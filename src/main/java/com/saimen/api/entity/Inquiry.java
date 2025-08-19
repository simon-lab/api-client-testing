package com.saimen.api.entity;

public class Inquiry {
    private String partnerReferenceNo;
    private String beneficiaryBankCode;
    private String beneficiaryAccountNo;
    private AdditionalInfo additionalInfo;

    public Inquiry() {
    }

    public Inquiry(String partnerReferenceNo, String beneficiaryBankCode, String beneficiaryAccountNo,
            AdditionalInfo additionalInfo) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.beneficiaryBankCode = beneficiaryBankCode;
        this.beneficiaryAccountNo = beneficiaryAccountNo;
        this.additionalInfo = additionalInfo;
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
}
