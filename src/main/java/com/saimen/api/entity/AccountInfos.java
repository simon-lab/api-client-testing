package com.saimen.api.entity;

public class AccountInfos {
    private String balanceType;
    private AvailableBalance availableBalance;

    public AccountInfos() {
    }

    public AccountInfos(String balanceType, AvailableBalance availableBalance) {
        this.balanceType = balanceType;
        this.availableBalance = availableBalance;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public AvailableBalance getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(AvailableBalance availableBalance) {
        this.availableBalance = availableBalance;
    }

}
