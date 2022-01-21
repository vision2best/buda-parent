package top.vlsion.buda.base.result;

import top.vlsion.buda.base.page.Page;

/**
 * 返回 包装工具类
 *
 * @author : zhanghuang
 * @date : 2021-12-31 11:23
 */
public class ResponseWrapperUtil {

    private static final String SUCCESS_CODE = "200";
    private static final String SUCCESS_MSG = "success";

    private static final String ERROR_CODE = "business_500";
    private static final String ERROR_MSG = "业务处理失败";

    private static final String SERVER_ERROR_CODE = "server_500";
    private static final String SERVER_ERROR_MSG = "服务器内部错误";

    /**
     * @param <T>
     * @return 成功无返回数据无分页信息
     */
    public static <T> ResponseWrapper<T> success() {
        return success(null, null);
    }

    /**
     * @param data
     * @param <T>
     * @return 成功有返回数据无分页信息
     */
    public static <T> ResponseWrapper<T> success(T data) {
        return success(data, null);
    }

    /**
     * @param data
     * @param page
     * @param <T>
     * @return 成功有数据有分页信息
     */
    public static <T> ResponseWrapper<T> success(T data, Page page) {
        ResponseWrapper<T> result = new ResponseWrapper<>();
        result.setData(data);
        result.setPage(page);
        result.setErrorCode(SUCCESS_CODE);
        result.setErrorMsg(SUCCESS_MSG);
        return result;
    }

    /**
     * @param <T>
     * @return 默认业务处理失败
     */
    public static <T> ResponseWrapper<T> fail() {
        return fail(ERROR_CODE, ERROR_MSG);
    }

    /**
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     * @param <T>
     * @return 自定义失败信息
     */
    private static <T> ResponseWrapper<T> fail(String errorCode, String errorMsg) {
        ResponseWrapper<T> result = new ResponseWrapper<>();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }

    /**
     * @param <T>
     * @return 服务器内部错误
     */
    public static <T> ResponseWrapper<T> serverError() {
        return fail(SERVER_ERROR_CODE, SERVER_ERROR_MSG);
    }
}
