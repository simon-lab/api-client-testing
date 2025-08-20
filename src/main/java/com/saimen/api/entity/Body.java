package com.saimen.api.entity;

public class Body {
    private String partnerReferenceNo;
    private String beneficiaryBankCode;
    private String beneficiaryAccountNo;
    private AdditionalInfo additionalInfo;
    private String sourceAccountNo;
    private String beneficiaryAccountName;
    private String transactionDate;
    private Amount amount;
    private String originalPartnerReferenceNo;
    private String serviceCode;
    private String accountNo;

    public Body() {
    }

    public Body(String partnerReferenceNo, String beneficiaryBankCode, String beneficiaryAccountNo,
            AdditionalInfo additionalInfo, String sourceAccountNo, String beneficiaryAccountName,
            String transactionDate, Amount amount, String originalPartnerReferenceNo, String serviceCode,
            String accountNo) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.beneficiaryBankCode = beneficiaryBankCode;
        this.beneficiaryAccountNo = beneficiaryAccountNo;
        this.additionalInfo = additionalInfo;
        this.sourceAccountNo = sourceAccountNo;
        this.beneficiaryAccountName = beneficiaryAccountName;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.serviceCode = serviceCode;
        this.accountNo = accountNo;
    }

    public String getPartnerReferenceNo() {
        return partnerReferenceNo;
    }

    public void setPartnerReferenceNo(String partnerReferenceNo) {
        this.partnerReferenceNo = partnerReferenceNo;
    }

    public String getBeneficiaryBankCode() {
        return beneficiaryBankCode;
    }

    public void setBeneficiaryBankCode(String beneficiaryBankCode) {
        this.beneficiaryBankCode = beneficiaryBankCode;
    }

    public String getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getSourceAccountNo() {
        return sourceAccountNo;
    }

    public void setSourceAccountNo(String sourceAccountNo) {
        this.sourceAccountNo = sourceAccountNo;
    }

    public String getBeneficiaryAccountName() {
        return beneficiaryAccountName;
    }

    public void setBeneficiaryAccountName(String beneficiaryAccountName) {
        this.beneficiaryAccountName = beneficiaryAccountName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getOriginalPartnerReferenceNo() {
        return originalPartnerReferenceNo;
    }

    public void setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

}
