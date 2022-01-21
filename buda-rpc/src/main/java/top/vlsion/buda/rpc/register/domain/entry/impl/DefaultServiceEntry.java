package top.vlsion.buda.rpc.register.domain.entry.impl;

import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;

import java.util.Objects;

/**
 * 服务信息
 *
 * @author : zhanghuang
 * @date : 2022-01-14 17:00
 */
public class DefaultServiceEntry implements ServiceEntry {

    private static final long serialVersionUID = 3020979862134073969L;
    /**
     * 服务标识
     */
    private String serviceId;

    /**
     * 服务描述
     */
    private String description;

    /**
     * 服务IP
     */
    private String ip;

    /**
     * 服务端口
     */
    private int port;

    /**
     * 服务权重
     */
    private String weight;

    @Override
    public String serviceId() {
        return serviceId;
    }

    public DefaultServiceEntry serviceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    @Override
    public String ip() {
        return ip;
    }

    public DefaultServiceEntry ip(String ip) {
        this.ip = ip;
        return this;
    }

    @Override
    public int port() {
        return port;
    }

    public DefaultServiceEntry port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public String weight() {
        return weight;
    }

    public DefaultServiceEntry weight(String weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public String description() {
        return description;
    }

    public DefaultServiceEntry description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "DefaultServiceEntry{" +
                "serviceId='" + serviceId + '\'' +
                ", description='" + description + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultServiceEntry that = (DefaultServiceEntry) o;
        if (port != that.port()) {
            return false;
        }
        if (!Objects.equals(serviceId, that.serviceId)) {
            return false;
        }
        return Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }
}