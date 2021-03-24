package com.numberone.system.domain;

import java.io.Serializable;

public class LisenceReq implements Serializable {

    /** 公司 */
    private String company;

    /** 机器码 */
    private String machineCode;

    /** 机器类型 */
    private String machineType;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
}
