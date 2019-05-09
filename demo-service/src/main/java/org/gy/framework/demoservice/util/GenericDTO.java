package org.gy.framework.demoservice.util;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 功能描述：基类
 */
public class GenericDTO implements Serializable {

    private static final long serialVersionUID = -8591463735721257554L;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
