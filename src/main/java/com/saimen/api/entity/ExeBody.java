package com.saimen.api.entity;

public class ExeBody {
    private String partnerReferenceNo;
    private String sourceAccountNo;
    private String beneficiaryBankCode;
    private String beneficiaryAccountNo;
    private String beneficiaryAccountName;
    private String transactionDate;
    private Amount amount;
    private AdditionalInfoExe additionalInfo;

    public ExeBody() {
    }

    public ExeBody(String partnerReferenceNo, String sourceAccountNo, String beneficiaryBankCode,
            String beneficiaryAccountNo, String beneficiaryAccountName, String transactionDate, Amount amount,
            AdditionalInfoExe additionalInfo) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.sourceAccountNo = sourceAccountNo;
        this.beneficiaryBankCode = beneficiaryBankCode;
        this.beneficiaryAccountNo = beneficiaryAccountNo;
        this.beneficiaryAccountName = beneficiaryAccountName;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
    }

    public String getPartnerReferenceNo() {
        return partnerReferenceNo;
    }

    public void setPartnerReferenceNo(String partnerReferenceNo) {
        this.partnerReferenceNo = partnerReferenceNo;
    }

    public String getSourceAccountNo() {
        return sourceAccountNo;
    }

    public void setSourceAccountNo(String sourceAccountNo) {
        this.sourceAccountNo = sourceAccountNo;
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

    public AdditionalInfoExe getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfoExe additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}
