package org.gy.framework.demoservice.util;

/**
 * 功能描述：基类
 */
public class BaseDTO extends GenericDTO {

    private static final long serialVersionUID = -4844223899615149397L;

    public static final String SUCCESS_CODE = ResponseCode.SUCCESS.getCode();

    public static final String SUCCESS_MESSAGE = ResponseCode.SUCCESS.getMessage();

    /**
     * 返回码，0000表示成功，其他表示失败
     */
    private String errorCode = SUCCESS_CODE;

    /**
     * 返回消息
     */
    private String errorMessage = SUCCESS_MESSAGE;

    /**
     * 成功标识
     */
    private boolean success = true;

    /**
     * 功能描述: 成功包装
     */
    public void wrapSuccess() {
        wrapResponse(ResponseCode.SUCCESS);
    }

    /**
     * 功能描述: 包装返回结果
     *
     * @param responseCode
     * @param placeholder
     */
    public void wrapResponse(ResponseCode responseCode,
                             Object... placeholder) {
        this.setErrorCode(responseCode.getCode());
        this.setErrorMessage(responseCode.buildMessage(placeholder));
    }

    /**
     * 功能描述: 包装返回结果
     *
     * @param e
     */
    public void wrapResponse(ServiceException e) {
        this.setErrorCode(e.getErrorCode());
        this.setErrorMessage(e.getErrorMessage());
    }

    /**
     * 是否成功判断
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取返回码，0000表示成功，其他表示失败
     *
     * @return errorCode 返回码，0000表示成功，其他表示失败
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置返回码，0000表示成功，其他表示失败
     *
     * @param errorCode 返回码，0000表示成功，其他表示失败
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        this.success = SUCCESS_CODE.equals(this.errorCode);
    }

    /**
     * 获取返回消息
     *
     * @return errorMessage 返回消息
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 设置返回消息
     *
     * @param errorMessage 返回消息
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
