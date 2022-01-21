package top.vlsion.buda.rpc.register.domain.message;

import top.vlsion.buda.rpc.common.domain.BaseRpc;

/**
 * 消息
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:21
 */
public interface NotifyMessage extends BaseRpc {
    NotifyMessageHeader header();

    Object body();
}
