package com.numberone.system.domain.resp;

import java.io.Serializable;

public class LisenceResp implements Serializable {

    /** 成功success,失败fail */
    private String result;

    /** 机器码 */
    private String machine;

    /** license */
    private String license;

    /** 机器类型 */
    private String messageInfo;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }
}
