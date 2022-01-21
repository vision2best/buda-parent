package top.vlsion.buda.rpc.register.domain.message.impl;

import top.vlsion.buda.rpc.register.domain.message.NotifyMessage;
import top.vlsion.buda.rpc.register.domain.message.NotifyMessageHeader;

/**
 * 通知消息
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:23
 */
public class DefaultNotifyMessage implements NotifyMessage {

    private static final long serialVersionUID = -2605831327763365800L;

    /**
     * 唯一序列号标识
     */
    private String seqId;

    /**
     * 头信息
     */
    private NotifyMessageHeader header;

    /**
     * 消息信息体
     */
    private Object body;

    @Override
    public String seqId() {
        return seqId;
    }

    @Override
    public DefaultNotifyMessage seqId(String traceId) {
        this.seqId = traceId;
        return this;
    }

    @Override
    public NotifyMessageHeader header() {
        return header;
    }

    public DefaultNotifyMessage header(NotifyMessageHeader header) {
        this.header = header;
        return this;
    }

    @Override
    public Object body() {
        return body;
    }

    public DefaultNotifyMessage body(Object body) {
        this.body = body;
        return this;
    }


}
