package top.vlsion.buda.rpc.common.domain;

/**
 * rpc返回体包装
 *
 * @author : zhanghuang
 * @date : 2022-01-17 15:46
 */
public interface RpcResponse extends BaseRpc {
    Throwable error();

    Object result();
}
