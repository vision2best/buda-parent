package top.vlsion.buda.rpc.register.simple.client.impl;

import io.netty.channel.Channel;
import top.vlsion.buda.common.utils.ArgsUtil;
import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;
import top.vlsion.buda.rpc.register.domain.message.NotifyMessage;
import top.vlsion.buda.rpc.register.domain.message.impl.NotifyMessages;
import top.vlsion.buda.rpc.register.simple.client.RegisterClientService;
import top.vlsion.buda.rpc.register.simple.constant.MessageTypeConst;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心客户端服务
 *
 * @author : zhanghuang
 * @date : 2022-01-17 13:49
 */
public class DefaultRegisterClientService implements RegisterClientService {

    private final Map<String, Set<Channel>> clients;

    public DefaultRegisterClientService() {
        this.clients = new ConcurrentHashMap<>();
    }

    /**
     * 客户端订阅服务
     */
    @Override
    public void subscribe(ServiceEntry serviceEntry, Channel clientChannel) {
        checkParam(serviceEntry);
        final String serviceId = serviceEntry.serviceId();
        Set<Channel> channelSet = clients.get(serviceId);
        if (channelSet == null) {
            channelSet = new HashSet<>();
        }
        channelSet.add(clientChannel);
        clients.put(serviceId, channelSet);
    }

    /**
     * 客户端取消订阅服务
     */
    @Override
    public void unSubscribe(ServiceEntry serviceEntry, Channel clientChannel) {
        checkParam(serviceEntry);
        String serviceId = serviceEntry.serviceId();
        Set<Channel> channelSet = clients.get(serviceId);
        if (channelSet == null || channelSet.isEmpty()) {
            return;
        }
        channelSet.remove(clientChannel);
        clients.put(serviceId, channelSet);
    }

    /**
     * 注册服务通知
     */
    @Override
    public void registerNotify(String serviceId, ServiceEntry serviceEntry) {
        ArgsUtil.notEmpty(serviceId, "serviceId");
        List<Channel> channels = clientChannelList(serviceId);
        if (channels.isEmpty()) {
            return;
        }
        for (Channel channel : channels) {
            NotifyMessage notifyMessage = NotifyMessages.create(MessageTypeConst.SERVER_REGISTER_NOTIFY_CLIENT_REQ,
                    serviceEntry);
            channel.writeAndFlush(notifyMessage);
        }
    }

    /**
     * 取消注册通知
     */
    @Override
    public void unRegisterNotify(String serviceId, ServiceEntry serviceEntry) {
        ArgsUtil.notEmpty(serviceId, "serviceId");

        List<Channel> clientChannelList = clientChannelList(serviceId);
        if (clientChannelList.isEmpty()) {
            return;
        }
        // 循环通知
        for (Channel channel : clientChannelList) {
            NotifyMessage notifyMessage = NotifyMessages.create(MessageTypeConst.SERVER_UNREGISTER_NOTIFY_CLIENT_REQ,
                    serviceEntry);
            channel.writeAndFlush(notifyMessage);
        }
    }

    /**
     * channel列表
     */
    @Override
    public Collection<Channel> channels() {
        Set<Channel> resultSet = new HashSet<>();
        Collection<Set<Channel>> channelCollection = clients.values();
        for (Set<Channel> set : channelCollection) {
            if (!set.isEmpty()) {
                resultSet.addAll(set);
            }
        }
        return resultSet;
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

    private List<Channel> clientChannelList(String serviceId) {
        ArgsUtil.notEmpty(serviceId, "serviceId");
        Set<Channel> channelSet = clients.get(serviceId);
        return new ArrayList<>(channelSet);
    }
}
