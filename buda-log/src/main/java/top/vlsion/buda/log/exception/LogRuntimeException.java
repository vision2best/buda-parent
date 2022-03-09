package top.vlsion.buda.log.exception;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 14:34
 */
public class LogRuntimeException extends RuntimeException {
    public LogRuntimeException() {
        super();
    }

    public LogRuntimeException(String message) {
        super(message);
    }

    public LogRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogRuntimeException(Throwable cause) {
        super(cause);
    }

}
