package tests.requestResponseValidation.ReusableMethod;

import org.testng.Assert;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;

public class assertionResponse {

    public static String assertJWT(JsonPath js, String jwt) {
        Allure.step("Check JWT");
        jwt = js.getString("jwt");

        Assert.assertNotNull(jwt, "jwt Tidak Ada");
        Assert.assertFalse(jwt.isEmpty(), "jwt Kosong");
        System.out.println("jwt: " + jwt);
        System.out.println("jwt sudah sesuai");
        return jwt;
    }

    public static void assertResponseCode(JsonPath js, int expectedLenght, String rcFormat) {

        Allure.step("Check Response Code");
        String responseCode = js.getString("responseCode");
        Assert.assertNotNull(responseCode, "Response Code Tidak ada");
        Assert.assertFalse(responseCode.isEmpty(), "Response Code Kosong");
        Assert.assertEquals(responseCode.length(), expectedLenght,
                "Isi Response Code Tidak " + expectedLenght + " Karakter");
        Assert.assertTrue(responseCode.matches(rcFormat), "Response Code Format Tidak Sesuai");
        System.out.println("Response Code sudah " + expectedLenght + " karakter");
        System.out.println("responseCode: " + responseCode);
        System.out.println("Response Code sudah sesuai");

    }

    public static void assertResponseMessage(JsonPath js, String expectedMessage) {
        Allure.step("Check Response Message");
        String responseMessage = js.getString("responseMessage");
        Assert.assertNotNull(responseMessage, "Response Message Tidak ada");
        Assert.assertFalse(responseMessage.isEmpty(), "Response Message Kosong");
        Assert.assertTrue(responseMessage.contains(expectedMessage), "Response Message Tidak Sesuai");
        // Assert.assertEquals(responseMessage, expectedMessage, "Response Message Tidak
        // Sesuai");
        System.out.println("responseMessage: " + responseMessage);
        System.out.println("Response Message sudah sesuai");
    }

    public static void assertBeneAccNo(JsonPath js) {
        Allure.step("Check beneficiary Account Number");
        String beneAccNo = js.getString("beneficiaryAccountNo");
        Assert.assertNotNull(beneAccNo, "Bene Acc No Tidak Ada");
        Assert.assertFalse(beneAccNo.isEmpty(), "Bener Acc No Kosong");
        System.out.println("beneficiaryAccountNo: " + beneAccNo);
        System.out.println("beneficiaryAccountNo sudah sesuai");
    }

    public static void assertBeneBankCode(JsonPath js, String oriBeneBankCode) {
        Allure.step("Check beneficiary Bank Code");
        String beneBankCode = js.getString("beneficiaryBankCode");
        Assert.assertNotNull(beneBankCode, "Bene Bank Code Tidak Ada");
        Assert.assertFalse(beneBankCode.isEmpty(), "Bener Bank Code Kosong");
        Assert.assertEquals(beneBankCode, oriBeneBankCode, "Bene Bank Code Tidak Sesuai");
        System.out.println("beneficiaryBankCode: " + beneBankCode);
        System.out.println("beneficiaryBankCode sudah sesuai");
    }

    public static String assertReferenceNo(JsonPath js, int expectedLenght) {
        Allure.step("Check Reference No");
        String referenceNo = js.getString("referenceNo");
        Assert.assertNotNull(referenceNo, "Reference No Tidak Ada");
        Assert.assertFalse(referenceNo.isEmpty(), "Reference No Kosong");
        Assert.assertEquals(referenceNo.length(), expectedLenght, "Reference No Tidak 12 Karakter");
        System.out.println("Reference No sudah 12 karakter");
        return referenceNo;
    }

    public static void assertOriginalReferenceNo(JsonPath js, String originalTransferExecutionReferenceNo) {
        Allure.step("Check Original Reference No");
        String originalReferenceNo = js.getString("originalReferenceNo");
        Assert.assertNotNull(originalReferenceNo, "Reference No Tidak Ada");
        Assert.assertFalse(originalReferenceNo.isEmpty(), "Reference No Kosong");
        Assert.assertEquals(originalReferenceNo.length(), 28, "Reference No Tidak 28 Karakter");
        Assert.assertEquals(originalReferenceNo, originalTransferExecutionReferenceNo,
                "Original Reference No tidak sama dengan Reference No Transfer Execution");
        System.out.println("Reference No sudah 12 karakter");
    }

    public static void assertBeneAccName(JsonPath js) {
        Allure.step("Check Bene Acc Name");
        String msgId = js.getString("beneficiaryAccountName");
        Assert.assertNotNull(msgId, "Bene Acc Name Tidak ada");
        System.out.println("Terdapat Bene Acc Name");
        Assert.assertFalse(msgId.isEmpty(), "Bene Acc Name Kosong");
        System.out.println("Bene Acc Name Tidak Kosong");
        System.out.println("Bene Acc Name Sudah Sesuai");
    }

    public static String assertMsgId(JsonPath js) {
        Allure.step("Check msgId");
        String msgId = js.getString("additionalInfo.msgId");
        Assert.assertNotNull(msgId, "msgId Tidak Ada");
        Assert.assertFalse(msgId.isEmpty(), "msgId kosong");
        Assert.assertTrue(msgId.length() <= 42, "Jumlah MSG ID lebih dari 42");
        System.out.println("msgId: " + msgId);
        System.out.println("msg Id sudah 42 karakter");
        return msgId;
    }

    public static void serviceCode(JsonPath js) {
        Allure.step("Check Service Code");
        String serviceCode = js.getString("senderInfo.serviceCode");
        Assert.assertNotNull(serviceCode, "Service Code Tidak ada");
        System.out.println("Terdapat Service Code");
        Assert.assertFalse(serviceCode.isEmpty(), "Service Code Kosong");
        System.out.println("Service Code Tidak Kosong");

        Assert.assertEquals(serviceCode, 18, "Service Code Tidak Sesuai Expected");

        System.out.println("Service Code Sudah Sesuai");

    }

    public static void value(JsonPath js) {
        Allure.step("Check Value");
        String value = js.getString("accountInfos.availableBalance.value");
        Assert.assertNotNull(value, "Value Tidak ada");
        System.out.println("Terdapat Value");
        Assert.assertFalse(value.isEmpty(), "Value Kosong");
        System.out.println("Value Tidak Kosong");
        String pattern = "^\\d{1,16}\\.\\d{2}$";
        Assert.assertTrue(value.matches(pattern),
                "Format tidak valid! Harus max 16 digit di depan, dan 2 di belakang koma.");
        System.out.println("Value Sudah Sesuai");

    }

    public static void currency(JsonPath js, String expectedCurrency) {
        Allure.step("Check Currency");
        String currency = js.getString("accountInfos.availableBalance.currency");
        Assert.assertNotNull(currency, "Currency Tidak ada");
        System.out.println("Terdapat Currency");
        Assert.assertFalse(currency.isEmpty(), "Currency Kosong");
        System.out.println("Currency Tidak Kosong");
        Assert.assertTrue(currency.matches("^[A-Z]{3}$"), "Currency tidak dalam format ISO 4217 (3 huruf kapital)");
        Assert.assertEquals(currency, expectedCurrency, "Currency Tidak Sesuai Expected");
        System.out.println("Currency Sudah Sesuai");

    }

    public static void balanceType(JsonPath js, String expectedType) {
        Allure.step("Check Balance Type");
        String balanceType = js.getString("accountInfos.balanceType");
        Assert.assertNotNull(balanceType, "Balance Type Tidak ada");
        System.out.println("Terdapat Balance Type");
        Assert.assertFalse(balanceType.isEmpty(), "Balance Type Kosong");
        System.out.println("Balance Type Tidak Kosong");
        Assert.assertTrue(balanceType.matches("^[A-Z]{3}$"),
                "Balance Type tidak dalam format ISO 4217 (3 huruf kapital)");
        Assert.assertEquals(balanceType, expectedType, "Balance Type Tidak Sesuai Expected");
        System.out.println("Currency Sudah Sesuai");

    }

    public static void sourceAccountNo(JsonPath js) {
        Allure.step("Check Source Account No");
        String sourceAccountNo = js.getString("sourceAccountNo");
        Assert.assertNotNull(sourceAccountNo, "Source Account No Tidak ada");
        System.out.println("Terdapat Source Account No");
        Assert.assertFalse(sourceAccountNo.isEmpty(), "Source Account No Kosong");
        System.out.println("Source Account No Tidak Kosong");
        Assert.assertTrue(sourceAccountNo.length() <= 19, "Jumlah karakter Source Account No lebih dari 19");
        System.out.println("Source Account No Sudah Sesuai");

    }

    public static void assertIsoRC(JsonPath js, String expectedRC) {
        Allure.step("Check iso Response Code");
        String isoResponseCode = js.getString("additionalInfo.isoResponseCode");
        Assert.assertNotNull(isoResponseCode, "isoRC Tidak Ada");
        Assert.assertFalse(isoResponseCode.isEmpty(), "isoRC Kosong");
        Assert.assertEquals(isoResponseCode.length(), 2, "isoRC tidak 2 karakter");
        System.out.println("iso Response Code sudah 2 karakter");
        Assert.assertEquals(isoResponseCode, expectedRC, "isoRC tidak sesuai");
        System.out.println("isoResponseCode: " + isoResponseCode);
        System.out.println("iso Response Code sudah sesuai");
    }

    public static void assertIsoMessage(JsonPath js, String expectedMessage) {
        Allure.step("Check iso Response Message");
        String isoResponseMessage = js.getString("additionalInfo.isoResponseMessage");
        Assert.assertNotNull(isoResponseMessage, "iso Response Message Tidak Ada");
        Assert.assertFalse(isoResponseMessage.isEmpty(), "iso Response Message Kosong");
        Assert.assertEquals(isoResponseMessage, expectedMessage, "iso Response Message Tidak Sesuai");
        System.out.println("iso Response Message: " + isoResponseMessage);
        System.out.println("iso Response Message sudah sesuai");
    }

}
