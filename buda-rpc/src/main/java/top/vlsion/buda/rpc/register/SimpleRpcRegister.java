package top.vlsion.buda.rpc.register;

import io.netty.channel.Channel;
import top.vlsion.buda.rpc.common.domain.RpcResponse;
import top.vlsion.buda.rpc.common.domain.impl.DefaultRpcResponse;
import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;
import top.vlsion.buda.rpc.register.domain.message.NotifyMessage;
import top.vlsion.buda.rpc.register.domain.message.body.ServerHeartbeatBody;
import top.vlsion.buda.rpc.register.domain.message.impl.NotifyMessages;
import top.vlsion.buda.rpc.register.simple.client.RegisterClientService;
import top.vlsion.buda.rpc.register.simple.constant.MessageTypeConst;
import top.vlsion.buda.rpc.register.simple.server.RegisterServerService;
import top.vlsion.buda.rpc.register.spi.RpcRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 简单的 rpc 注册服务实现
 * （1）各种关系的关系服务类
 * （2）各种关系之间的通讯类
 * （3）domain 层
 *
 * @author : zhanghuang
 * @date : 2022-01-17 15:21
 */
public class SimpleRpcRegister implements RpcRegister {
    /**
     * 服务端服务
     */
    private final RegisterServerService registerServerService;

    /**
     * 客户端服务
     */
    private final RegisterClientService registerClientService;

    /**
     * 服务端心跳检测
     */
    private final Map<String, Long> serverHeartbeatMap;

    /**
     * 服务端心跳定时处理
     */
    private final ScheduledExecutorService serverHeartBeatExecutor;

    public SimpleRpcRegister(RegisterServerService registerServerService, RegisterClientService registerclientService) {
        this.registerServerService = registerServerService;
        this.registerClientService = registerclientService;
        this.serverHeartbeatMap = new ConcurrentHashMap<>();
        //定时任务
        this.serverHeartBeatExecutor = Executors.newSingleThreadScheduledExecutor();
        final Runnable runnable = new ServerHeartBeatThread();
        //scheduleAtFixedRate ，是以上一个任务开始的时间计时，120秒过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。
        //scheduleWithFixedDelay，是以上一个任务结束时开始计时，120秒过去后，立即执行。
        //command：执行线程
        //initialDelay：初始化延时
        //period：两次开始执行最小间隔时间
        //unit：计时单位
        serverHeartBeatExecutor.scheduleAtFixedRate(runnable, 60, 60, TimeUnit.SECONDS);

    }

    @Override
    public void register(ServiceEntry serviceEntry, Channel channel) {
        List<ServiceEntry> serviceEntryList = registerServerService.register(serviceEntry, channel);
        // 通知监听者
        registerClientService.registerNotify(serviceEntry.serviceId(), serviceEntry);
    }

    @Override
    public void unRegister(ServiceEntry serviceEntry) {
        List<ServiceEntry> serviceEntries = registerServerService.unRegister(serviceEntry);
        registerClientService.unRegisterNotify(serviceEntry.serviceId(), serviceEntry);
    }

    @Override
    public void subscribe(ServiceEntry serviceEntry, Channel channel) {
        registerClientService.subscribe(serviceEntry, channel);
    }

    @Override
    public void unSubscribe(ServiceEntry serviceEntry, Channel channel) {
        registerClientService.unSubscribe(serviceEntry, channel);
    }

    @Override
    public void lookUp(String seqId, ServiceEntry clientEntry, Channel channel) {
        final String serviceId = clientEntry.serviceId();
        List<ServiceEntry> serviceEntryList = registerServerService.lookUp(serviceId);

        // 回写
        // 为了复用原先的相应结果，此处直接使用 rpc response
        RpcResponse rpcResponse = DefaultRpcResponse.newInstance().seqId(seqId)
                .result(serviceEntryList);
        NotifyMessage notifyMessage = NotifyMessages.create(MessageTypeConst.CLIENT_LOOK_UP_SERVER_RESP, seqId, rpcResponse);
        channel.writeAndFlush(notifyMessage);
    }

    @Override
    public void serverHeartbeat(ServerHeartbeatBody heartbeatBody, Channel channel) {
        String ip = heartbeatBody.ip();
        int port = heartbeatBody.port();

        // 存储当前的时间
        String key = ip + ":" + port;
        long time = heartbeatBody.time();
        serverHeartbeatMap.put(key, time);
    }

    private List<String> getExpiredServiceList(long limitMills) {
        // 遍历集合，找到过期的服务端
        List<String> expiredServices = new ArrayList<>();
        long currentMills = System.currentTimeMillis();
        for (Map.Entry<String, Long> entry : serverHeartbeatMap.entrySet()) {
            String key = entry.getKey();
            long time = entry.getValue();

            long differMills = currentMills - time;
            //如果服务端的服务向注册中心发送心跳最后一次的时间与当前的时间差大于2分钟 则认为服务失效
            if (differMills > limitMills) {
                expiredServices.add(key);
            }
        }
        return expiredServices;
    }

    private class ServerHeartBeatThread implements Runnable {
        private final long limitMills = 120 * 1000;

        @Override
        public void run() {
            // 遍历集合，找到过期的服务端
            List<String> expiredServices = getExpiredServiceList(limitMills);
            if (expiredServices.isEmpty()) {
                return;
            }
            // 遍历过期的服务端，执行
            for (String ipPort : expiredServices) {
                Collection<ServiceEntry> serviceEntries = registerServerService.serviceEntries(ipPort);
                if (serviceEntries == null) {
                    continue;
                }
                for (ServiceEntry serviceEntry : serviceEntries) {
                    unRegister(serviceEntry);
                }
            }
        }
    }
}

