package org.gy.framework.demoservice.dto;

import org.gy.framework.demoservice.util.GenericDTO;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 功能描述：
 */
public class TestRequestDTO extends GenericDTO {
    private static final long serialVersionUID = -2118488636226920413L;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    @Size(min = 2, max = 12, message = "名称长度必须是2至12个字符")
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    @Range(min = 20, max = 40, message = "年龄必须介于20至40岁之间")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
