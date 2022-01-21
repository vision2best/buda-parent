package top.vlsion.buda.rpc.register.domain.entry.impl;

import top.vlsion.buda.rpc.register.domain.entry.ServiceEntry;

/**
 * 服务信息构造器
 *
 * @author : zhanghuang
 * @date : 2022-01-17 13:27
 */
public class ServiceEntryBuilder {

    private ServiceEntryBuilder() {
    }

    public static ServiceEntry create(final String serviceId,
                                      final String ip,
                                      final int port) {
        DefaultServiceEntry serviceEntry = new DefaultServiceEntry();
        serviceEntry.serviceId(serviceId).ip(ip).port(port);
        return serviceEntry;
    }

    public static ServiceEntry create(final String serviceId) {
        return create(serviceId, null, 0);
    }
}
