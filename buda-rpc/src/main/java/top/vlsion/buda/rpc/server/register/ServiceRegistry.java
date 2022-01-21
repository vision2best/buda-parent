package top.vlsion.buda.rpc.server.register;

/**
 * 服务注册
 *
 * @author : zhanghuang
 * @date : 2022-01-20 15:14
 */
public interface ServiceRegistry {
    /**
     * rpc 暴露端口
     *
     * @param port 端口
     * @return this
     */
    ServiceRegistry port(final int port);

    /**
     * 注册服务实现
     *
     * @param serviceId   服务唯一标识
     * @param serviceImpl 服务实现
     * @return this
     */
    ServiceRegistry register(final String serviceId, final Object serviceImpl);

    /**
     * 暴露所有服务
     *
     * @return this
     */
    ServiceRegistry expose();

    /**
     * 注册中心地址信息
     *
     * @param addresses 地址信息
     * @return this
     */
    ServiceRegistry registerCenter(final String addresses);
}
