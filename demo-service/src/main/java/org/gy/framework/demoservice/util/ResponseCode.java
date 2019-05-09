package org.gy.framework.demoservice.util;

import java.text.MessageFormat;

/**
 * 功能描述：异常码统一定义
 */
public enum ResponseCode {

    SUCCESS("0000", "操作成功"),

    E9997("9997", "系统异常：{0}"),

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

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取格式化消息，带错误码
     *
     * @param responseCode
     * @param placeholder
     * @return
     */
    public static String buildMessageWithCode(ResponseCode responseCode,
                                              Object... placeholder) {
        return new StringBuilder("[").append(responseCode.getCode()).append("]").append(buildMessage(responseCode, placeholder)).toString();
    }

    /**
     * 获取格式化消息，不带错误码
     *
     * @param responseCode
     * @param placeholder
     * @return
     */
    public static String buildMessage(ResponseCode responseCode,
                                      Object... placeholder) {
        return MessageFormat.format(responseCode.getMessage(), placeholder);
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
     * 功能描述: 获取格式化消息，不带错误码
     *
     * @param placeholder
     * @return
     */
    public String buildMessage(Object... placeholder) {
        return buildMessage(this, placeholder);
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

}
