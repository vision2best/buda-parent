package top.vlsion.buda.rpc.register.api.config;

/**
 * 注册中心配置信息接口
 *
 * @author : zhanghuang
 * @date : 2022-01-14 16:51
 */
public interface RegisterConfig {
    /**
     * 注册中心服务端口
     *
     * @param port 端口号
     * @return this
     */
    RegisterConfig port(final int port);

    /**
     * 启动服务
     *
     * @return this
     */
    RegisterConfig start();
}
