package top.vlsion.buda.base.result;

import top.vlsion.buda.base.page.Page;

import java.io.Serializable;

/**
 * @author : zhanghuang
 * @date : 2021-12-31 11:14
 */
public class ResponseWrapper<T> implements Serializable {

    /**
     * 本次请求是否满足预期
     */
    private Boolean success = true;

    /**
     * 分页信息
     */
    private Page page;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 异常编码
     */
    private String errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
