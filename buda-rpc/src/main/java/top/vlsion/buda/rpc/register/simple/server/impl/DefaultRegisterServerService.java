package top.vlsion.buda.rpc.register.simple.server.impl;

import io.netty.channel.Channel;
import top.vlsion.buda.common.utils.ArgsUtil;
import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;
import top.vlsion.buda.rpc.register.simple.server.RegisterServerService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心服务端服务
 *
 * @author : zhanghuang
 * @date : 2022-01-17 13:49
 */
public class DefaultRegisterServerService implements RegisterServerService {
    /**
     * 存放对应的 map 信息
     */

    private final Map<String, Set<ServiceEntry>> serviceEntryMap;

    /**
     * 服务端对应的 channel 信息
     */
    private final Map<ServiceEntry, Channel> serviceEntryChannelMap;

    public DefaultRegisterServerService() {
        this.serviceEntryMap = new ConcurrentHashMap<>();
        this.serviceEntryChannelMap = new ConcurrentHashMap<>();
    }


    /**
     * 注册服务
     */
    @Override
    public List<ServiceEntry> register(ServiceEntry serviceEntry, Channel channel) {
        checkParam(serviceEntry);
        final String serviceId = serviceEntry.serviceId();
        Set<ServiceEntry> serviceEntrySet = serviceEntryMap.get(serviceId);
        if (serviceEntrySet == null) {
            serviceEntrySet = new HashSet<>();
        }
        serviceEntrySet.add(serviceEntry);
        serviceEntryMap.put(serviceId, serviceEntrySet);
        serviceEntryChannelMap.put(serviceEntry, channel);
        // 返回更新后的结果
        return new ArrayList<>(serviceEntrySet);
    }

    /**
     * 注销服务
     */
    @Override
    public List<ServiceEntry> unRegister(ServiceEntry serviceEntry) {
        checkParam(serviceEntry);

        final String serviceId = serviceEntry.serviceId();
        Set<ServiceEntry> serviceEntrySet = serviceEntryMap.get(serviceId);
        if (serviceEntrySet == null) {
            return new ArrayList<>();
        }

        serviceEntrySet.remove(serviceEntry);
        serviceEntryMap.put(serviceId, serviceEntrySet);

        serviceEntryChannelMap.remove(serviceEntry);

        // 返回更新后的结果
        return new ArrayList<>(serviceEntrySet);
    }

    /**
     * 发现服务
     */
    @Override
    public List<ServiceEntry> lookUp(String serviceId) {
        ArgsUtil.notEmpty(serviceId, "serviceId");
        Set<ServiceEntry> serviceEntrySet = serviceEntryMap.get(serviceId);
        return new ArrayList<>(serviceEntrySet);
    }

    /**
     * channel列表
     */
    @Override
    public Collection<Channel> channels() {
        return serviceEntryChannelMap.values();
    }

    /**
     * 所有的服务明细
     */
    @Override
    public Collection<ServiceEntry> serviceEntries() {
        return serviceEntryChannelMap.keySet();
    }

    /**
     * 所有的指定地址端口的服务明细
     */
    @Override
    public Collection<ServiceEntry> serviceEntries(String ipPort) {
        Collection<ServiceEntry> serviceEntries = serviceEntries();
        Set<ServiceEntry> set = new HashSet<>();
        for (ServiceEntry serviceEntry : serviceEntries) {
            String ip = serviceEntry.ip();
            int port = serviceEntry.port();
            String key = ip + ":" + port;

            if (key.equals(ipPort)) {
                set.add(serviceEntry);
            }
        }
        return set;
    }

    /**
     * 参数校验
     *
     * @param serviceEntry 入参信息
     */
    private void checkParam(final ServiceEntry serviceEntry) {
        ArgsUtil.notNull(serviceEntry, "serverEntry");
        ArgsUtil.notEmpty(serviceEntry.serviceId(), "serverEntry.serviceId");
    }
}
