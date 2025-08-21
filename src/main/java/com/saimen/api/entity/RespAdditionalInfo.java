package com.saimen.api.entity;

public class RespAdditionalInfo {
    private String msgId;
    private String isoResponseCode;
    private String isoResponseMessage;

    public RespAdditionalInfo() {
    }

    public RespAdditionalInfo(String msgId, String isoResponseCode, String isoResponseMessage) {
        this.msgId = msgId;
        this.isoResponseCode = isoResponseCode;
        this.isoResponseMessage = isoResponseMessage;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getIsoResponseCode() {
        return isoResponseCode;
    }

    public void setIsoResponseCode(String isoResponseCode) {
        this.isoResponseCode = isoResponseCode;
    }

    public String getIsoResponseMessage() {
        return isoResponseMessage;
    }

    public void setIsoResponseMessage(String isoResponseMessage) {
        this.isoResponseMessage = isoResponseMessage;
    }

}
