package org.gy.framework.demoservice.dto;

import java.io.Serializable;
import java.util.Date;

public class ConfigInfoDTO implements Serializable {

    private static final long serialVersionUID = -52258782873588770L;

    private Long id;

    private Integer configType;

    private String configCode;

    private String configName;

    private Integer configStatus;

    private Date createTime;

    private Date updateTime;

    private String employeeCode;

    private String remark;

    private String pConfigCode;

    private String pConfigName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Integer getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(Integer configStatus) {
        this.configStatus = configStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getpConfigCode() {
        return pConfigCode;
    }

    public void setpConfigCode(String pConfigCode) {
        this.pConfigCode = pConfigCode;
    }

    public String getpConfigName() {
        return pConfigName;
    }

    public void setpConfigName(String pConfigName) {
        this.pConfigName = pConfigName;
    }
}
