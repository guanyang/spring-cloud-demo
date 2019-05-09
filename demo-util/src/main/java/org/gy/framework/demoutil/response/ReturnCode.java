package org.gy.framework.demoutil.response;

import java.text.MessageFormat;

/**
 * 功能描述：返回码声明
 * <p>
 * <strong>说明：</strong>
 * </p>
 * <ul>
 * <li>根据具体业务定义具体状态码</li>
 * <li>状态码枚举格式：E{状态码}</li>
 * </ul>
 * 
 */
public enum ReturnCode {

    SUCCESS("0000", "操作成功"),

    E9998("9998", "参数错误：{0}"),

    E9999("9999", "系统异常，请稍后重试！");

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;

    ReturnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取格式化消息，带错误码
     * 
     * @param returnCode
     * @param placeholder
     * @return
     */
    public static String buildMessageWithCode(ReturnCode returnCode,
                                              Object... placeholder) {
        return new StringBuilder("[").append(returnCode.getCode()).append("]").append(buildMessage(returnCode, placeholder)).toString();
    }

    /**
     * 获取格式化消息，不带错误码
     * 
     * @param returnCode
     * @param placeholder
     * @return
     */
    public static String buildMessage(ReturnCode returnCode,
                                      Object... placeholder) {
        return MessageFormat.format(returnCode.getMessage(), placeholder);
    }

    /**
     * 功能描述: 获取格式化消息，带错误码
     * 
     * @param placeholder
     * @return
     */
    public String buildMessageWithCode(Object... placeholder) {
        return buildMessageWithCode(this, placeholder);
    }

    /**
     * 获取返回码
     * 
     * @return code 返回码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取返回消息
     * 
     * @return message 返回消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 功能描述: 获取格式化消息，不带错误码
     * 
     * @param placeholder
     * @return
     */
    public String buildMessage(Object... placeholder) {
        return buildMessage(this, placeholder);
    }

}
