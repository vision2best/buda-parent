package top.vlsion.buda.rpc.common.domain;

import java.io.Serializable;

/**
 * 基础rpc
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:28
 */
public interface BaseRpc extends Serializable {
    /**
     * 获取rpc请求唯一标识
     *
     * @return 唯一标识
     */
    String seqId();

    /**
     * 设置rpc请求唯一标识
     *
     * @param traceId 标识
     * @return this
     */
    BaseRpc seqId(String traceId);
}
