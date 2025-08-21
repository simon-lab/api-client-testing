package com.saimen.api.entity;

public class AvailableBalance {
    private String value;
    private String currency;

    public AvailableBalance() {
    }

    public AvailableBalance(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
