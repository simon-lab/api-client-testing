package com.saimen.api.entity;

public class AdditionalInfoExe {
    private String msgId;
    private String disbDescription;
    private String disbCategory;
    private SenderInfo senderInfo;
    private String dspsign;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getDisbDescription() {
        return disbDescription;
    }

    public void setDisbDescription(String disbDescription) {
        this.disbDescription = disbDescription;
    }

    public String getDisbCategory() {
        return disbCategory;
    }

    public void setDisbCategory(String disbCategory) {
        this.disbCategory = disbCategory;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public String getDspsign() {
        return dspsign;
    }

    public void setDspsign(String dspsign) {
        this.dspsign = dspsign;
    }

    public AdditionalInfoExe() {
    }

}
