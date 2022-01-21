package top.vlsion.buda.rpc.common.exception;

/**
 * rpc运行时异常封装
 *
 * @author : zhanghuang
 * @date : 2022-01-17 16:06
 */
public class RpcRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1924771979563767032L;

    public RpcRuntimeException() {
    }

    public RpcRuntimeException(String message) {
        super(message);
    }

    public RpcRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcRuntimeException(Throwable cause) {
        super(cause);
    }

    public RpcRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
