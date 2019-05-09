package org.gy.framework.demoservice.dto;


import org.gy.framework.demoservice.util.GenericDTO;

/**
 * 功能描述：
 *
 * @author guanyang/14050360
 * @version 2.0.0
 */
public class TestResponseDTO extends GenericDTO {

    private static final long serialVersionUID = 6413105856633106662L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
