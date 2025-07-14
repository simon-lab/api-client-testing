package tests.requestResponseValidation.ReusableMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;

public class assertionRequest {

    public static void assertXTimeStamp(JsonPath js) {
        Allure.step("Check X-Time-Stamp");
        String timestamp = js.getString("X-TIMESTAMP");
        String regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timestamp);
        Assert.assertTrue(matcher.matches());
        Assert.assertNotNull(timestamp, "X-Time-Stamp Tidak ada");
        System.out.println("Terdapat XTimeStamp");
        Assert.assertFalse(timestamp.isEmpty(), "X-Time-Stamp Kosong");
        System.out.println("XTimeStamp Tidak Kosong");
        System.out.println("XTimeStamp: " + timestamp);
        System.out.println("XTimeStamp sudah sesuai format");
    }

    public static void assertXClientKey(JsonPath js) {
        Allure.step("Check X-Client-Key");
        String clientKey = js.getString("X-CLIENT-KEY");
        Assert.assertNotNull(clientKey, "X-Client-Key Tidak ada");
        System.out.println("Terdapat X-Client-Key");
        Assert.assertFalse(clientKey.isEmpty(), "X-Client-Key Kosong");
        System.out.println("X-Client-Key Tidak Kosong");
        System.out.println("X-Client-Key: " + clientKey);
        System.out.println("X-Client-Key sudah sesuai format");
    }

    public static void assertXSignature(String Signature) {

    }

    public static void assertChannelID(JsonPath js, String expectedChannelID) {
        Allure.step("Check Channel-ID");
        String channelId = js.getString("CHANNEL-ID");
        Assert.assertNotNull(channelId, "Channel-ID Tidak ada");
        System.out.println("Terdapat Channel-ID");
        Assert.assertFalse(channelId.isEmpty(), "Channel-ID Kosong");
        System.out.println("Channel-ID Tidak Kosong");
        Assert.assertEquals(channelId, expectedChannelID, "Channel Id Tidak Sesuai Expected");
        System.out.println("Channel-ID Sudah Sesuai");

    }

    public static void assertPartnerReferenceNo(JsonPath js, String expectedPartnerReferenceNo) {
        Allure.step("Check Partner Reference No");
        String partnerReferenceNo = js.getString("partnerReferenceNo");
        Assert.assertNotNull(partnerReferenceNo, "partnerReferenceNo Tidak ada");
        System.out.println("Terdapat partnerReferenceNo");
        Assert.assertFalse(partnerReferenceNo.isEmpty(), "partnerReferenceNo Kosong");
        System.out.println("partnerReferenceNo Tidak Kosong");
        Assert.assertTrue(partnerReferenceNo.length() <= 64, "Jumlah karakter partnerReferenceNo lebih dari 64");
        Assert.assertEquals(partnerReferenceNo, expectedPartnerReferenceNo,
                "Partner Reference No Tidak Sesuai Expected");
        System.out.println("partnerReferenceNo Sudah Sesuai");

    }

    public static void beneBankCode(JsonPath js, String expectedBeneBankCode) {
        Allure.step("Check Beneficiary Account Code");
        String beneBankCode = js.getString("beneficiaryBankCode");
        String transferService = js.getString("additionalInfo.transferService");
        Assert.assertNotNull(beneBankCode, "Beneficiary Account Code tidak ada");
        System.out.println("Terdapat Beneficiary Account Code");
        Assert.assertFalse(beneBankCode.isEmpty(), "Beneficiary Account Code Kosong");
        System.out.println("Beneficiary Account Code Tidak Kosong");

        if (transferService == "REALTIME ONLINE") {
            Assert.assertTrue(beneBankCode.length() <= 3,
                    "Jumlah karakter Beneficiary Account Code REALTIME ONLINE lebih dari 3");
        } else if (transferService == "BI FAST") {
            Assert.assertTrue(beneBankCode.length() <= 8,
                    "Jumlah karakter Beneficiary Account Code BI FAST lebih dari 8");
        } else {
            Assert.fail("Transfer service tidak dikenali");
        }
        Assert.assertEquals(beneBankCode, expectedBeneBankCode, "Bene Bank Code Tidak Sesuai Expected");
        System.out.println("Beneficiary Account Code Sudah Sesuai");

    }

    public static void beneAccNo(JsonPath js, String expectedBeneAccNo) {
        Allure.step("Check Beneficiary Account No");
        String beneAccNo = js.getString("beneficiaryAccountNo");
        Assert.assertNotNull(beneAccNo, "Beneficiary Account No Tidak ada");
        System.out.println("Terdapat Beneficiary Account No");
        Assert.assertFalse(beneAccNo.isEmpty(), "Beneficiary Account No Kosong");
        System.out.println("Beneficiary Account No Tidak Kosong");
        Assert.assertTrue(beneAccNo.length() <= 19, "Jumlah karakter Beneficiary Account No lebih dari 64");
        Assert.assertEquals(beneAccNo, expectedBeneAccNo, "Bene Acc No Tidak Sesuai Expected");
        System.out.println("Beneficiary Account No Sudah Sesuai");

    }

    public static void transferService(JsonPath js, String expectedTransferService) {
        Allure.step("Check Transfer Service");
        String transferService = js.getString("additionalInfo.transferService");
        Assert.assertNotNull(transferService, "Transfer Service Tidak ada");
        System.out.println("Terdapat Transfer Service");
        Assert.assertFalse(transferService.isEmpty(), "Transfer Service Kosong");
        System.out.println("Transfer Service Tidak Kosong");
        Assert.assertTrue(transferService.length() <= 19, "Jumlah karakter Transfer Service lebih dari 64");
        Assert.assertEquals(transferService, expectedTransferService, "Transfer Service Tidak Sesuai Expected");
        System.out.println("Transfer Service Sudah Sesuai");

    }

    public static void value(JsonPath js, String expectedValue) {
        Allure.step("Check Value");
        String value = js.getString("additionalInfo.amount.value");
        Assert.assertNotNull(value, "Value Tidak ada");
        System.out.println("Terdapat Value");
        Assert.assertFalse(value.isEmpty(), "Value Kosong");
        System.out.println("Value Tidak Kosong");
        String pattern = "^\\d{1,16}\\.\\d{2}$";
        Assert.assertTrue(value.matches(pattern),
                "Format tidak valid! Harus max 16 digit di depan, dan 2 di belakang koma.");
        Assert.assertEquals(value, expectedValue, "Value Tidak Sesuai Expected");
        System.out.println("Value Sudah Sesuai");

    }

    public static void currency(JsonPath js, String expectedCurrency) {
        Allure.step("Check Currency");
        String currency = js.getString("additionalInfo.amount.currency");
        Assert.assertNotNull(currency, "Currency Tidak ada");
        System.out.println("Terdapat Currency");
        Assert.assertFalse(currency.isEmpty(), "Currency Kosong");
        System.out.println("Currency Tidak Kosong");
        Assert.assertTrue(currency.matches("^[A-Z]{3}$"), "Currency tidak dalam format ISO 4217 (3 huruf kapital)");
        Assert.assertEquals(currency, expectedCurrency, "Currency Tidak Sesuai Expected");
        System.out.println("Currency Sudah Sesuai");

    }

    public static void dspSign(JsonPath js, String jwt) {
        Allure.step("Check dsp sign");
        String dspSign = js.getString("additionalInfo.dspsign");
        Assert.assertNotNull(dspSign, "dsp sign Tidak ada");
        System.out.println("Terdapat dsp sign");
        Assert.assertFalse(dspSign.isEmpty(), "dsp sign Kosong");
        System.out.println("dsp sign Tidak Kosong");
        Assert.assertEquals(dspSign, "Bearer " + jwt, "dsp sign Tidak Sesuai Expected");
        System.out.println("dspSign  Sudah Sesuai");

    }

}
