package top.vlsion.buda.rpc.register.spi;

import io.netty.channel.Channel;
import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;
import top.vlsion.buda.rpc.register.domain.message.body.ServerHeartbeatBody;

/**
 * 注册中心接口
 *
 * @author : zhanghuang
 * @date : 2022-01-17 15:21
 */
public interface RpcRegister {
    /**
     * 注册当前服务信息
     * 订阅了这个 serviceId 的所有客户端
     *
     * @param serviceEntry 注册当前服务信息
     * @param channel      channel
     */
    void register(final ServiceEntry serviceEntry, Channel channel);

    /**
     * 注销当前服务信息
     *
     * @param serviceEntry 注册当前服务信息
     */
    void unRegister(final ServiceEntry serviceEntry);

    /**
     * 监听服务信息
     * （1）监听之后，如果有任何相关的机器信息发生变化，则进行推送。
     * （2）内置的信息，需要传送 ip 信息到注册中心。
     *
     * @param serviceEntry 客户端明细信息
     * @param channel      频道信息
     */
    void subscribe(final ServiceEntry serviceEntry, final Channel channel);

    /**
     * 取消监听服务信息
     * <p>
     * （1）将改服务从客户端的监听列表中移除即可。
     *
     * @param server  客户端明细信息
     * @param channel 频道信息
     */
    void unSubscribe(final ServiceEntry server, final Channel channel);

    /**
     * 启动时查询 serviceId 对应的所有服务端信息
     *
     * @param seqId       请求标识
     * @param clientEntry 客户端查询明细
     * @param channel     频道信息
     */
    void lookUp(String seqId, ServiceEntry clientEntry, final Channel channel);

    /**
     * 服务端心跳检测
     *
     * @param heartbeatBody 心跳对象
     * @param channel       频道信息
     */
    void serverHeartbeat(final ServerHeartbeatBody heartbeatBody, final Channel channel);

}
