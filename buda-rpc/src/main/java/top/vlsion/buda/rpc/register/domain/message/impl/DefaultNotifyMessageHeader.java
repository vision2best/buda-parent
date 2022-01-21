package top.vlsion.buda.rpc.register.domain.message.impl;

import top.vlsion.buda.rpc.register.domain.message.NotifyMessageHeader;

/**
 * 通知消息头信息
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:24
 */
public class DefaultNotifyMessageHeader implements NotifyMessageHeader {

    private static final long serialVersionUID = -7766357259660694061L;

    private String type;

    @Override
    public String type() {
        return null;
    }

    public DefaultNotifyMessageHeader type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "DefaultNotifyMessageHeader{" +
                "type='" + type + '\'' +
                '}';
    }
}