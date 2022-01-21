package top.vlsion.buda.base.exception;

/**
 * 异常封装
 *
 * @author : zhanghuang
 * @date : 2021-12-31 13:27
 */
public class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String code, String message) {
        this(code + "\n" + message);
    }

    public BaseException instance(ExceptionSupportEnum exception) {
        return new BaseException(exception.getCode(), exception.getMessage());
    }

    public BaseException instance(ExceptionSupportEnum exception, String message) {
        return new BaseException(exception.getCode(), exception.getMessage() + "\n" + message);
    }


}
