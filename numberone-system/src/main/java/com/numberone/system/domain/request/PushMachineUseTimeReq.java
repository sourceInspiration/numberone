package com.numberone.system.domain.request;

import java.io.Serializable;
import java.util.Date;

public class PushMachineUseTimeReq implements Serializable {

    /** 公司 */
    private String companyCode;

    /** 机器码 */
    private String machineCode;

    /** 机器类型 */
    private String machineType;
    /**
     * 新增使用时长
     */
    private Long addMachineUseTime;

    /** 更新者 */
    private String updateBy;
    /** 更新时间 */
    private Date updateTime;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public Long getAddMachineUseTime() {
        return addMachineUseTime;
    }

    public void setAddMachineUseTime(Long addMachineUseTime) {
        this.addMachineUseTime = addMachineUseTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
