package top.vlsion.buda.log.exception;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 14:34
 */
public class LogException extends Exception {

    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }
}
