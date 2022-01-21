package top.vlsion.buda.rpc.register.simple.client;

import io.netty.channel.Channel;
import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;

import java.util.Collection;

/**
 * @author : zhanghuang
 * @date : 2022-01-14 16:54
 */
public interface RegisterClientService {
    /**
     * 客户端订阅服务
     */
    void subscribe(final ServiceEntry serviceEntry, final Channel clientChannel);

    /**
     * 客户端取消订阅服务
     */
    void unSubscribe(final ServiceEntry serviceEntry, final Channel clientChannel);

    /**
     * 注册服务通知
     */
    void registerNotify(final String serviceId, final ServiceEntry serviceEntry);

    /**
     * 取消注册通知
     */
    void unRegisterNotify(final String serviceId, final ServiceEntry serviceEntry);

    /**
     * channel列表
     */
    Collection<Channel> channels();
}
