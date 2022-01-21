package top.vlsion.buda.rpc.register.domain.message.impl;

import top.vlsion.buda.common.utils.ArgsUtil;
import top.vlsion.buda.common.utils.UUIDUtil;
import top.vlsion.buda.rpc.register.domain.message.NotifyMessage;
import top.vlsion.buda.rpc.register.domain.message.NotifyMessageHeader;

/**
 * 通知消息辅助类
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:30
 */
public final class NotifyMessages {
    private NotifyMessages() {
    }

    /**
     * 创建消息
     *
     * @param type 消息类型 {@link top.vlsion.buda.rpc.register.simple.constant.MessageTypeConst}
     * @param body 消息体
     * @return 消息
     */
    public static NotifyMessage create(final String type,
                                       final Object body) {
        String seqId = UUIDUtil.getUUIDNoSplit();
        return create(seqId, type, body);
    }

    /**
     * 创建消息
     *
     * @param seqId 消息唯一标识
     * @param type  消息类型 {@link top.vlsion.buda.rpc.register.simple.constant.MessageTypeConst}
     * @param body  消息体
     * @return 消息
     */
    public static NotifyMessage create(String seqId, String type, Object body) {
        DefaultNotifyMessage notifyMessage = new DefaultNotifyMessage();
        DefaultNotifyMessageHeader messageHeader = new DefaultNotifyMessageHeader();
        notifyMessage.seqId(seqId).header(messageHeader.type(type)).body(body);
        return notifyMessage;
    }

    /**
     * 获取 消息类型
     *
     * @param notifyMessage 消息
     * @return 通知类型 {@link top.vlsion.buda.rpc.register.simple.constant.MessageTypeConst}
     */
    public static String type(final NotifyMessage notifyMessage) {
        NotifyMessageHeader header = header(notifyMessage);
        return header.type();
    }

    /**
     * 获取 消息头信息
     *
     * @param notifyMessage 消息
     * @return 头信息
     */
    private static NotifyMessageHeader header(final NotifyMessage notifyMessage) {
        ArgsUtil.notNull(notifyMessage, "registerMessage");
        NotifyMessageHeader messageHeader = notifyMessage.header();
        ArgsUtil.notNull(messageHeader, "messageHeader");
        return messageHeader;
    }

}
