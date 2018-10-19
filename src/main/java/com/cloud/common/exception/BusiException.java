package com.cloud.common.exception;

/**
 * 公用业务异常类
 */
public class BusiException extends Exception {

    private static final long serialVersionUID = -7282812607019775599L;

    /*** 抛错的标题 **/
    private String errorName;

    /*** 抛错的在base_code里面的编码 **/
    private Integer errorCode;

    /** 参数化配置 **/
    private String[] exceptionParam;

    public BusiException() {
        super();
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getExceptionParam() {
        return exceptionParam;
    }

    public void setExceptionParam(String[] exceptionParam) {
        this.exceptionParam = exceptionParam;
    }

    /**
     * 增加参数支持 格式化带参数的 错误信息
     * @param errorCode  配置在base-code里面的错误代码
     * @param ex  报错的详细信息
     * @param args  base_code里面的错误
     */
    public BusiException(Integer errorCode, Throwable ex, String ...args) {
        super(null, ex);
        this.errorCode = errorCode;
        this.exceptionParam = args;
    }

    /**
     * 增加参数支持 格式化带参数的 错误信息
     * @param errorCode  配置在base-code里面的错误代码
     * @param args  base_code里面的错误
     */
    public BusiException(Integer errorCode, String ...args) {
        this.errorCode = errorCode;
        this.exceptionParam = args;
    }

    public BusiException(String message, Throwable ex) {
        super(message, null);
    }

    public BusiException(String message) {
        super(message);
    }

    public BusiException(Throwable ex) {
        super(ex);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}