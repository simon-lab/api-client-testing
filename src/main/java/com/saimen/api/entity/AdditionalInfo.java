package com.saimen.api.entity;

public class AdditionalInfo {
    private String transferService;
    private Amount amount;
    private String msgId;
    private String disbDescription;
    private String disbCategory;
    private SenderInfo senderInfo;
    private String dspsign;

    public AdditionalInfo() {
    }

    public AdditionalInfo(String transferService, Amount amount, String msgId, String disbDescription,
            String disbCategory, SenderInfo senderInfo, String dspsign) {
        this.transferService = transferService;
        this.amount = amount;
        this.msgId = msgId;
        this.disbDescription = disbDescription;
        this.disbCategory = disbCategory;
        this.senderInfo = senderInfo;
        this.dspsign = dspsign;
    }

    public String getTransferService() {
        return transferService;
    }

    public void setTransferService(String transferService) {
        this.transferService = transferService;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

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

}
