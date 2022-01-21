package top.vlsion.buda.rpc.register.domain.message;

import java.io.Serializable;

/**
 * 消息头信息
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:22
 */
public interface NotifyMessageHeader extends Serializable {
    String type();
}
