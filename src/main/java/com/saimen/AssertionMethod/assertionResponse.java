package com.saimen.AssertionMethod;

import com.saimen.api.dto.ValidationContext;
import com.saimen.api.entity.Response;

public class assertionResponse {

    // public static String assertJWT(Response rsp, String jwt) {
    // jwt = js.getString("jwt");

    // Assert.assertNotNull(jwt, "jwt Tidak Ada");
    // Assert.assertFalse(jwt.isEmpty(), "jwt Kosong");
    // System.out.println("jwt: " + jwt);
    // System.out.println("jwt sudah sesuai");
    // return jwt;
    // }

    public static void assertResponseCode(Response rsp, int expectedLenght, String rcFormat,
            ValidationContext ctx) {
        String responseCode = rsp.getResponseCode();

        if (responseCode == null) {
            ctx.addError("Response Code Tidak ada");
        } else if (responseCode.isEmpty()) {
            ctx.addError("Response Code kosong");
        } else if (responseCode.length() != expectedLenght) {
            ctx.addError("Isi Response Code Tidak " + expectedLenght + " Karakter");
        } else if (!responseCode.matches(rcFormat)) {
            ctx.addError("Response Code Format Tidak Sesuai");
        }

    }

    public static void assertResponseMessage(Response rsp, String expectedMessage, ValidationContext ctx) {
        String responseMessage = rsp.getResponseMessage();

        if (responseMessage == null) {
            ctx.addError("Response Message Tidak ada");
        } else if (responseMessage.isEmpty()) {
            ctx.addError("Response Message kosong");
        } else if (!responseMessage.equals(expectedMessage)) {
            ctx.addError("Response Message Tidak Sesuai");
        }

    }

    public static void assertBeneAccNo(Response rsp, ValidationContext ctx) {
        String beneAccNo = rsp.getBeneficiaryAccountNo();

        if (beneAccNo == null) {
            ctx.addError("beneficiary Account Number Tidak ada");
        } else if (beneAccNo.isEmpty()) {
            ctx.addError("beneficiary Account Number kosong");
        }

    }

    // public static void assertBeneBankCode(Response rsp, String
    // oriBeneBankCode,
    // ValidationContext ctx) {
    // String beneBankCode = rsp.getBene;
    // Assert.assertNotNull(beneBankCode, "Bene Bank Code Tidak Ada");
    // Assert.assertFalse(beneBankCode.isEmpty(), "Bener Bank Code Kosong");
    // Assert.assertEquals(beneBankCode, oriBeneBankCode, "Bene Bank Code Tidak
    // Sesuai");
    // System.out.println("beneficiaryBankCode: " + beneBankCode);
    // System.out.println("beneficiaryBankCode sudah sesuai");
    // }

    public static void assertReferenceNo(Response rsp, int expectedLenght, ValidationContext ctx) {

        String referenceNo = rsp.getReferenceNo();

        if (referenceNo == null) {
            ctx.addError("Reference No Tidak ada");
        } else if (referenceNo.isEmpty()) {
            ctx.addError("Reference No kosong");
        } else if (referenceNo.length() != expectedLenght) {
            ctx.addError("Isi Reference No Tidak " + expectedLenght + " Karakter");
        }

    }

    public static void assertOriginalReferenceNo(Response rsp, int expectedLenght, ValidationContext ctx) {

        String originalReferenceNo = rsp.getOriginalReferenceNo();

        if (originalReferenceNo == null) {
            ctx.addError("Original Reference No Tidak ada");
        } else if (originalReferenceNo.isEmpty()) {
            ctx.addError("Original Reference No kosong");
        } else if (originalReferenceNo.length() != expectedLenght) {
            ctx.addError("Isi Original Reference No Tidak " + expectedLenght + " Karakter");
        }

    }

    public static void assertBeneAccName(Response rsp, ValidationContext ctx) {

        String beneAccName = rsp.getBeneficiaryAccountName();

        if (beneAccName == null) {
            ctx.addError("Bene Acc Name Tidak ada");
        } else if (beneAccName.isEmpty()) {
            ctx.addError("Bene Acc Name kosong");
        }

    }

    public static void assertMsgId(Response rsp, int expectedLenght, ValidationContext ctx) {

        var ai = rsp.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Original Reference No Tidak ada");
            return;
        }

        String msgId = rsp.getAdditionalInfo().getMsgId();

        if (msgId == null) {
            ctx.addError("Original Reference No Tidak ada");
        } else if (msgId.isEmpty()) {
            ctx.addError("Original Reference No kosong");
        } else if (msgId.length() >= expectedLenght) {
            ctx.addError("Isi MSG ID lebih dari " + expectedLenght + " Karakter");
        }

    }

    public static void serviceCode(Response rsp, String expectedServiceCode, ValidationContext ctx) {

        String serviceCode = rsp.getServiceCode();

        if (serviceCode == null) {
            ctx.addError("Service Code Tidak ada");
        } else if (serviceCode.isEmpty()) {
            ctx.addError("Service Code kosong");
        } else if (!serviceCode.equals(ctx)) {
            ctx.addError("Service Code Tidak Sesuai Expected");
        }

    }

    public static void value(Response rsp, ValidationContext ctx) {

        var ai = rsp.getAccountInfos();
        if (ai == null) {
            ctx.addError("Value Tidak ada");
            return;
        }

        String value = rsp.getAccountInfos().getAvailableBalance().getValue();
        String pattern = "^\\d{1,16}\\.\\d{2}$";

        if (value == null) {
            ctx.addError("Value Tidak ada");
        } else if (value.isEmpty()) {
            ctx.addError("Value kosong");
        } else if (!value.matches(pattern)) {
            ctx.addError("Format tidak valid! Harus max 16 digit di depan, dan 2 di belakang koma.");
        }

    }

    public static void currency(Response rsp, String expectedCurrency, ValidationContext ctx) {

        var ai = rsp.getAccountInfos();
        if (ai == null) {
            ctx.addError("Currency Tidak ada");
            return;
        }

        String currency = rsp.getAccountInfos().getAvailableBalance().getCurrency();

        if (currency == null) {
            ctx.addError("Currency Tidak ada");
        } else if (currency.isEmpty()) {
            ctx.addError("Currency kosong");
        } else if (!currency.matches("^[A-Z]{3}$")) {
            ctx.addError("Currency tidak dalam format ISO 4217 (3 huruf kapital)");
        } else if (!currency.equals(expectedCurrency)) {
            ctx.addError("Currency Tidak Sesuai Expected");
        }

    }

    public static void balanceType(Response rsp, String expectedType, ValidationContext ctx) {

        var ai = rsp.getAccountInfos();
        if (ai == null) {
            ctx.addError("Balance Type Tidak ada");
            return;
        }

        String balanceType = rsp.getAccountInfos().getBalanceType();

        if (balanceType == null) {
            ctx.addError("Balance Type Tidak ada");
        } else if (balanceType.isEmpty()) {
            ctx.addError("Balance Type kosong");
        } else if (!balanceType.matches("^[A-Z]{3}$")) {
            ctx.addError("Balance Type tidak dalam format ISO 4217 (3 huruf kapital)");
        } else if (!balanceType.equals(expectedType)) {
            ctx.addError("Balance Type Tidak Sesuai Expected");
        }

    }

    // public static void balanceTypeBalance(Response rsp, String
    // expectedType,
    // ValidationContext ctx) {
    // String balanceType = js.getString("accountInfos.balanceType");
    // Assert.assertNotNull(balanceType, "Balance Type Tidak ada");
    // System.out.println("Terdapat Balance Type");
    // Assert.assertFalse(balanceType.isEmpty(), "Balance Type Kosong");
    // System.out.println("Balance Type Tidak Kosong");
    // Assert.assertTrue(balanceType.equalsIgnoreCase(expectedType), "Balance Type
    // Tidak Sesuai Expected");
    // System.out.println("Currency Sudah Sesuai");

    // }

    public static void sourceAccountNo(Response rsp, int expectedMaxLenght, ValidationContext ctx) {

        String sourceAccountNo = rsp.getSourceAccountNo();

        if (sourceAccountNo == null) {
            ctx.addError("Source Account No Tidak ada");
        } else if (sourceAccountNo.isEmpty()) {
            ctx.addError("Source Account No kosong");
        } else if (sourceAccountNo.length() >= expectedMaxLenght) {
            ctx.addError("Jumlah karakter Source Account No lebih dari 19");
        }

    }

    public static void assertIsoRC(Response rsp, String expectedRC, ValidationContext ctx) {

        String isoResponseCode = rsp.getAdditionalInfo().getIsoResponseCode();

        var ai = rsp.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("isoRC Tidak ada");
            return;
        }

        if (isoResponseCode == null) {
            ctx.addError("isoRC Tidak ada");
        } else if (isoResponseCode.isEmpty()) {
            ctx.addError("isoRC kosong");
        } else if (isoResponseCode.length() != 2) {
            ctx.addError("isoRC tidak 2 karakter");
        } else if (!isoResponseCode.equals(expectedRC)) {
            ctx.addError("isoRC Tidak Sesuai Expected");
        }

    }

    public static void assertIsoMessage(Response rsp, String expectedMessage, ValidationContext ctx) {

        var ai = rsp.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("iso Response Message Tidak ada");
            return;
        }

        String isoResponseMessage = rsp.getAdditionalInfo().getIsoResponseMessage();

        if (isoResponseMessage == null) {
            ctx.addError("iso Response Message Tidak ada");
        } else if (isoResponseMessage.isEmpty()) {
            ctx.addError("iso Response Message kosong");
        } else if (!isoResponseMessage.equals(expectedMessage)) {
            ctx.addError("iso Response Message Tidak Sesuai Expected");
        }

    }

}
