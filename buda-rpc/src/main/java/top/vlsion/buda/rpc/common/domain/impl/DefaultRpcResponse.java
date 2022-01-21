package top.vlsion.buda.rpc.common.domain.impl;


import top.vlsion.buda.rpc.common.domain.RpcResponse;

/**
 * 默认 rpc 响应
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:28
 */
public class DefaultRpcResponse implements RpcResponse {
    private static final long serialVersionUID = 8328501833989511603L;
    /**
     * 唯一标识
     */
    private String seqId;

    /**
     * 异常信息
     */
    private Throwable error;

    /**
     * 响应结果
     */
    private Object result;

    public static DefaultRpcResponse newInstance() {
        return new DefaultRpcResponse();
    }

    @Override
    public String seqId() {
        return seqId;
    }

    @Override
    public DefaultRpcResponse seqId(String seqId) {
        this.seqId = seqId;
        return this;
    }

    @Override
    public Throwable error() {
        return error;
    }

    public DefaultRpcResponse error(Throwable error) {
        this.error = error;
        return this;
    }

    @Override
    public Object result() {
        return result;
    }

    public DefaultRpcResponse result(Object result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "DefaultRpcResponse{" +
                "seqId='" + seqId + '\'' +
                ", error=" + error +
                ", result=" + result +
                '}';
    }
}
