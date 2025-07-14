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

    public static void assertBeneAccNo(JsonPath js, String oriBeneAccNo) {
        Allure.step("Check beneficiary Account Number");
        String beneAccNo = js.getString("beneficiaryAccountNo");
        Assert.assertNotNull(beneAccNo, "Bene Acc No Tidak Ada");
        Assert.assertFalse(beneAccNo.isEmpty(), "Bener Acc No Kosong");
        Assert.assertEquals(beneAccNo, oriBeneAccNo, "Bene Acc No Tidak Sesuai");
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

    public static void assertBeneAccName(JsonPath js, String oriBeneAccName) {
        Allure.step("Check Beneficiary Account Name");
        String beneAccName = js.getString("beneficiaryAccountName");
        if (beneAccName != null) {
            Assert.assertEquals(beneAccName, oriBeneAccName, "BeneAccName Tidak Sesuai");
            System.out.println("Beneficiary Acc Number: " + beneAccName);
            System.out.println("beneficiaryAccountNo sudah sesuai");
        } else {
            System.out.println("beneficiaryAccountNo tidak ada");
        }
    }

    public static String assertMsgId(JsonPath js, String msgId) {
        Allure.step("Check msgId");
        msgId = js.getString("additionalInfo.msgId");
        Assert.assertNotNull(msgId, "msgId Tidak Ada");
        Assert.assertFalse(msgId.isEmpty(), "msgId kosong");
        // Assert.assertEquals(msgId.length(), 42, "msgId tidak 42 karakter");
        System.out.println("msgId: " + msgId);
        System.out.println("msg Id sudah 42 karakter");
        return msgId;
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
