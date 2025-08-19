package com.saimen.api.entity;

public class AdditionalInfo {
    private String transferService;
    private Amount amount;
    private String dspSign;

    public AdditionalInfo() {
    }

    public AdditionalInfo(String transferService, Amount amount, String dspSign) {
        this.transferService = transferService;
        this.amount = amount;
        this.dspSign = dspSign;
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

    public String getDspSign() {
        return dspSign;
    }

    public void setDspSign(String dspSign) {
        this.dspSign = dspSign;
    }
}
