package org.gy.framework.demoservice.util;

/**
 * 功能描述：接口封装异常
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -5909003637017754887L;

    /**
     * 异常码
     */
    private String errorCode;

    /**
     * 异常描述
     */
    private String errorMessage;

    public ServiceException(String errorCode, String errorMessage) {
        super(errorCode + ":" + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ServiceException(String errorCode, String errorMessage, Throwable e) {
        super(errorCode + ":" + errorMessage, e);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 获取异常码
     *
     * @return errorCode 异常码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置异常码
     *
     * @param errorCode 异常码
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 获取异常描述
     *
     * @return errorMessage 异常描述
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 设置异常描述
     *
     * @param errorMessage 异常描述
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
