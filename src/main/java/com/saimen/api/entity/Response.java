package com.saimen.api.entity;

public class Response {
    private String responseCode;
    private String responseMessage;
    private String beneficiaryAccountName;
    private String beneficiaryAccountNo;
    private String referenceNo;
    private RespAdditionalInfo additionalInfo;

    private String originalReferenceNo;
    private String serviceCode;
    private String sourceAccountNo;
    private String latestTransactionStatus;
    private String transactionStatusDesc;

    private AccountInfos accountInfos;

    public Response() {
    }

    public Response(String responseCode, String responseMessage, String beneficiaryAccountName,
            String beneficiaryAccountNo, String referenceNo, RespAdditionalInfo additionalInfo,
            String originalReferenceNo, String serviceCode, String sourceAccountNo, String latestTransactionStatus,
            String transactionStatusDesc, AccountInfos accountInfos) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.beneficiaryAccountName = beneficiaryAccountName;
        this.beneficiaryAccountNo = beneficiaryAccountNo;
        this.referenceNo = referenceNo;
        this.additionalInfo = additionalInfo;
        this.originalReferenceNo = originalReferenceNo;
        this.serviceCode = serviceCode;
        this.sourceAccountNo = sourceAccountNo;
        this.latestTransactionStatus = latestTransactionStatus;
        this.transactionStatusDesc = transactionStatusDesc;
        this.accountInfos = accountInfos;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getBeneficiaryAccountName() {
        return beneficiaryAccountName;
    }

    public void setBeneficiaryAccountName(String beneficiaryAccountName) {
        this.beneficiaryAccountName = beneficiaryAccountName;
    }

    public String getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public RespAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(RespAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getOriginalReferenceNo() {
        return originalReferenceNo;
    }

    public void setOriginalReferenceNo(String originalReferenceNo) {
        this.originalReferenceNo = originalReferenceNo;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSourceAccountNo() {
        return sourceAccountNo;
    }

    public void setSourceAccountNo(String sourceAccountNo) {
        this.sourceAccountNo = sourceAccountNo;
    }

    public String getLatestTransactionStatus() {
        return latestTransactionStatus;
    }

    public void setLatestTransactionStatus(String latestTransactionStatus) {
        this.latestTransactionStatus = latestTransactionStatus;
    }

    public String getTransactionStatusDesc() {
        return transactionStatusDesc;
    }

    public void setTransactionStatusDesc(String transactionStatusDesc) {
        this.transactionStatusDesc = transactionStatusDesc;
    }

    public AccountInfos getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(AccountInfos accountInfos) {
        this.accountInfos = accountInfos;
    }

}
