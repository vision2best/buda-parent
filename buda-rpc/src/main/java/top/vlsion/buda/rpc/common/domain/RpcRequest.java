package top.vlsion.buda.rpc.common.domain;

import java.util.List;

/**
 * rpc请求体包装
 *
 * @author : zhanghuang
 * @date : 2022-01-17 15:46
 */
public interface RpcRequest extends BaseRpc {
    long createTime();

    String serviceId();

    String methodName();

    List<String> paramTypeNames();

    Object[] paramValues();

    Class returnType();

    long timeout();
}

