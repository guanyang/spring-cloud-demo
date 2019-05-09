package org.gy.framework.demoservice.util;

import java.io.Serializable;

/**
 * 功能描述：接口统一返回DTO
 */
public final class ResponseDTO<T extends Serializable> extends BaseDTO {

    private static final long serialVersionUID = 8612413429607062775L;

    /**
     * 结果集
     */
    private T data;

    /**
     * 获取结果集
     *
     * @return data 结果集
     */
    public T getData() {
        return data;
    }

    /**
     * 设置结果集
     *
     * @param data 结果集
     */
    public void setData(T data) {
        this.data = data;
    }

}
