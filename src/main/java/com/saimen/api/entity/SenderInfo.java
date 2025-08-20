package com.saimen.api.entity;

public class SenderInfo {
    private String name;
    private String accountType;
    private String accountInstId;
    private String country;
    private String city;
    private String identificationType;
    private String identificationNumber;

    public SenderInfo() {
    }

    public SenderInfo(String name, String accountType, String accountInstId, String country, String city,
            String identificationType, String identificationNumber) {
        this.name = name;
        this.accountType = accountType;
        this.accountInstId = accountInstId;
        this.country = country;
        this.city = city;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountInstId() {
        return accountInstId;
    }

    public void setAccountInstId(String accountInstId) {
        this.accountInstId = accountInstId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

}
