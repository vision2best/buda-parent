package top.vlsion.buda.rpc.register.simple.server;

import io.netty.channel.Channel;
import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;

import java.util.Collection;
import java.util.List;

/**
 * @author : zhanghuang
 * @date : 2022-01-14 16:54
 */
public interface RegisterServerService {
    /**
     * 注册服务
     */
    List<ServiceEntry> register(final ServiceEntry serviceEntry, Channel channel);

    /**
     * 注销服务
     */
    List<ServiceEntry> unRegister(final ServiceEntry serviceEntry);

    /**
     * 发现服务
     */
    List<ServiceEntry> lookUp(final String serviceId);

    /**
     * channel列表
     */
    Collection<Channel> channels();

    /**
     * 所有的服务明细
     */
    Collection<ServiceEntry> serviceEntries();

    /**
     * 所有的指定地址端口的服务明细
     */
    Collection<ServiceEntry> serviceEntries(String ipPort);

}
