package top.vlsion.buda.rpc.register.domain.entry;

import java.io.Serializable;

/**
 * 注册的服务信息
 *
 * @author : zhanghuang
 * @date : 2022-01-14 16:59
 */
public interface ServiceEntry extends Serializable {
    /**
     * 服务标识
     *
     * @return serviceId
     */
    String serviceId();

    /**
     * 服务IP
     *
     * @return ip
     */
    String ip();

    /**
     * 服务端口
     *
     * @return port
     */
    int port();

    /**
     * 权重
     *
     * @return weight
     */
    String weight();

    /**
     * 服务描述
     *
     * @return desc
     */
    String description();
}
