package com.numberone.system.domain.resp;

import java.io.Serializable;

public class BaseResp implements Serializable {

    /** 成功success,失败fail */
    private String code;

    /** 错误码信息 */

    private String messageInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }
}
