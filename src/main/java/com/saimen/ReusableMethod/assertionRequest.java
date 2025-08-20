package com.saimen.ReusableMethod;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.testng.Assert;

import com.saimen.api.entity.Body;
import com.saimen.api.entity.Header;
import com.saimen.constant.Expected;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;

public class assertionRequest {

    public static void assertXTimeStamp(Header head) {
        Allure.step("Check X-Time-Stamp");
        String timestamp = head.getxTimeStamp();
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

    // public static void assertXClientKey(Header head, String expectedClientKey) {
    // Allure.step("Check X-Client-Key");
    // String clientKey = head.ge;
    // Assert.assertNotNull(clientKey, "X-Client-Key Tidak ada");
    // System.out.println("Terdapat X-Client-Key");
    // Assert.assertFalse(clientKey.isEmpty(), "X-Client-Key Kosong");
    // System.out.println("X-Client-Key Tidak Kosong");
    // System.out.println("X-Client-Key: " + clientKey);
    // Assert.assertEquals(clientKey, expectedClientKey, "X-Client-Key Tidak Sesuai
    // Expected");
    // System.out.println("X-Client-Key sudah sesuai format");
    // }

    public static void assertXSignature(String Signature) {

    }

    public static void assertXPartnerId(Header head, String partnerID) {
        Allure.step("Check Partner ID");
        String xPartnerId = head.getxPartnerId();
        Assert.assertNotNull(xPartnerId, "Partner ID Tidak ada");
        System.out.println("Terdapat Partner ID");
        Assert.assertFalse(xPartnerId.isEmpty(), "Partner ID Kosong");
        System.out.println("Partner ID Tidak Kosong");
        Assert.assertEquals(xPartnerId, partnerID, "Partner Id Tidak Sesuai Expected");
        System.out.println("Partner ID: " + xPartnerId);
        System.out.println("Partner ID sudah sesuai format");
    }

    public static void assertXExternalId(Header head) {
        Allure.step("Check External ID");
        String xExternalId = head.getxExternalId();
        Assert.assertNotNull(xExternalId, "External ID Tidak ada");
        System.out.println("Terdapat External ID");
        Assert.assertFalse(xExternalId.isEmpty(), "External ID Kosong");
        System.out.println("External ID Tidak Kosong");
        System.out.println("External ID: " + xExternalId);
        System.out.println("External ID sudah sesuai format");
    }

    public static void assertChannelID(Header head, String expectedChannelID) {
        Allure.step("Check Channel-ID");
        String channelId = head.getChannelId();
        Assert.assertNotNull(channelId, "Channel-ID Tidak ada");
        System.out.println("Terdapat Channel-ID");
        Assert.assertFalse(channelId.isEmpty(), "Channel-ID Kosong");
        System.out.println("Channel-ID Tidak Kosong");
        Assert.assertEquals(channelId, expectedChannelID, "Channel Id Tidak Sesuai Expected");
        System.out.println("Channel-ID Sudah Sesuai");

    }

    public static void assertPartnerReferenceNo(Body body) {
        Allure.step("Check Partner Reference No");
        String partnerReferenceNo = body.getPartnerReferenceNo();
        Assert.assertNotNull(partnerReferenceNo, "partnerReferenceNo Tidak ada");
        System.out.println("Terdapat partnerReferenceNo");
        Assert.assertFalse(partnerReferenceNo.isEmpty(), "partnerReferenceNo Kosong");
        System.out.println("partnerReferenceNo Tidak Kosong");
        Assert.assertTrue(partnerReferenceNo.length() <= 64, "Jumlah karakter partnerReferenceNo lebih dari 64");
        System.out.println("partnerReferenceNo Sudah Sesuai");

    }

    public static void assertOriPartnerReferenceNo(Body body) {
        Allure.step("Check Partner Reference No");
        String partnerReferenceNo = body.getOriginalPartnerReferenceNo();
        Assert.assertNotNull(partnerReferenceNo, "partnerReferenceNo Tidak ada");
        System.out.println("Terdapat partnerReferenceNo");
        Assert.assertFalse(partnerReferenceNo.isEmpty(), "partnerReferenceNo Kosong");
        System.out.println("partnerReferenceNo Tidak Kosong");
        Assert.assertTrue(partnerReferenceNo.length() <= 64, "Jumlah karakter partnerReferenceNo lebih dari 64");
        System.out.println("partnerReferenceNo Sudah Sesuai");

    }

    public static void sourceAccountNo(Body body) {
        Allure.step("Check Source Account No");
        String sourceAccountNo = body.getSourceAccountNo();
        Assert.assertNotNull(sourceAccountNo, "Source Account No Tidak ada");
        System.out.println("Terdapat Source Account No");
        Assert.assertFalse(sourceAccountNo.isEmpty(), "Source Account No Kosong");
        System.out.println("Source Account No Tidak Kosong");
        Assert.assertTrue(sourceAccountNo.length() <= 19, "Jumlah karakter Source Account No lebih dari 19");
        System.out.println("Source Account No Sudah Sesuai");

    }

    public static void beneBankCode(Body body, Expected testData) {
        Allure.step("Check Beneficiary Bank Code");
        String beneBankCode = body.getBeneficiaryBankCode();
        String transferService = testData.TRANSFERSERVICE;
        Assert.assertNotNull(beneBankCode, "Beneficiary Bank Code tidak ada");
        System.out.println("Terdapat Beneficiary Bank Code");
        Assert.assertFalse(beneBankCode.isEmpty(), "Beneficiary Bank Code Kosong");
        System.out.println("Beneficiary Bank Code Tidak Kosong");

        if (transferService.equalsIgnoreCase("REALTIME ONLINE")) {
            Assert.assertTrue(beneBankCode.length() <= 3,
                    "Jumlah karakter Beneficiary Bank Code REALTIME ONLINE lebih dari 3");
        } else if (transferService.equalsIgnoreCase("BI FAST")) {
            Assert.assertTrue(beneBankCode.length() <= 8,
                    "Jumlah karakter Beneficiary Bank Code BI FAST lebih dari 8");
        } else {
            Assert.fail("Transfer service tidak dikenali");
        }
        System.out.println("Beneficiary Bank Code Sudah Sesuai");

    }

    public static void beneAccNo(Body body) {
        Allure.step("Check Beneficiary Account No");
        String beneAccNo = body.getBeneficiaryAccountNo();
        Assert.assertNotNull(beneAccNo, "Beneficiary Account No Tidak ada");
        System.out.println("Terdapat Beneficiary Account No");
        Assert.assertFalse(beneAccNo.isEmpty(), "Beneficiary Account No Kosong");
        System.out.println("Beneficiary Account No Tidak Kosong");
        Assert.assertTrue(beneAccNo.length() <= 19, "Jumlah karakter Beneficiary Account No lebih dari 19");
        System.out.println("Beneficiary Account No Sudah Sesuai");

    }

    public static void beneAccName(Body body) {
        Allure.step("Check Beneficiary Account Name");
        String beneAccName = body.getBeneficiaryAccountName();
        Assert.assertNotNull(beneAccName, "Beneficiary Account Name Tidak ada");
        System.out.println("Terdapat Beneficiary Account Name");
        Assert.assertFalse(beneAccName.isEmpty(), "Beneficiary Account Name Kosong");
        System.out.println("Beneficiary Account Name Sudah Sesuai");

    }

    public static void trxDate(Body body) {
        Allure.step("Check Transaction Date");
        String trxDate = body.getTransactionDate();
        Assert.assertNotNull(trxDate, "Transaction Date Tidak ada");
        System.out.println("Terdapat Transaction Date");
        Assert.assertFalse(trxDate.isEmpty(), "Transaction Date Kosong");
        String pattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2}$";
        Assert.assertTrue(trxDate.matches(pattern), "Format tidak valid! Harus YYYY-MM-DDTHH:mm:ss+HH:mm");
        System.out.println("Transaction Date Tidak Kosong");
        System.out.println("Transaction Date Sudah Sesuai");

    }

    public static void transferService(Body body, String expectedTransferService) {
        Allure.step("Check Transfer Service");
        String transferService = body.getAdditionalInfo().getTransferService();
        Assert.assertNotNull(transferService, "Transfer Service Tidak ada");
        System.out.println("Terdapat Transfer Service");
        Assert.assertFalse(transferService.isEmpty(), "Transfer Service Kosong");
        System.out.println("Transfer Service Tidak Kosong");
        Assert.assertTrue(transferService.length() <= 19, "Jumlah karakter Transfer Service lebih dari 64");
        Assert.assertEquals(transferService, expectedTransferService, "Transfer Service Tidak Sesuai Expected");
        System.out.println("Transfer Service Sudah Sesuai");

    }

    public static void value(Body body) {
        Allure.step("Check Value");
        String value = body.getAdditionalInfo().getAmount().getValue();
        Assert.assertNotNull(value, "Value Tidak ada");
        System.out.println("Terdapat Value");
        Assert.assertFalse(value.isEmpty(), "Value Kosong");
        System.out.println("Value Tidak Kosong");
        String pattern = "^\\d{1,16}\\.\\d{2}$";
        Assert.assertTrue(value.matches(pattern),
                "Format tidak valid! Harus max 16 digit di depan, dan 2 di belakang koma.");
        System.out.println("Value Sudah Sesuai");

    }

    public static void currency(Body body, String expectedCurrency) {
        Allure.step("Check Currency");
        String currency = body.getAdditionalInfo().getAmount().getCurrency();
        Assert.assertNotNull(currency, "Currency Tidak ada");
        System.out.println("Terdapat Currency");
        Assert.assertFalse(currency.isEmpty(), "Currency Kosong");
        System.out.println("Currency Tidak Kosong");
        Assert.assertTrue(currency.matches("^[A-Z]{3}$"), "Currency tidak dalam format ISO 4217 (3 huruf kapital)");
        Assert.assertEquals(currency, expectedCurrency, "Currency Tidak Sesuai Expected");
        System.out.println("Currency Sudah Sesuai");

    }

    public static void valueExe(Body body) {
        Allure.step("Check Value");
        String value = body.getAmount().getValue();
        Assert.assertNotNull(value, "Value Tidak ada");
        System.out.println("Terdapat Value");
        Assert.assertFalse(value.isEmpty(), "Value Kosong");
        System.out.println("Value Tidak Kosong");
        String pattern = "^\\d{1,16}\\.\\d{2}$";
        Assert.assertTrue(value.matches(pattern),
                "Format tidak valid! Harus max 16 digit di depan, dan 2 di belakang koma.");
        System.out.println("Value Sudah Sesuai");

    }

    public static void currencyExe(Body body, String expectedCurrency) {
        Allure.step("Check Currency");
        String currency = body.getAmount().getCurrency();
        Assert.assertNotNull(currency, "Currency Tidak ada");
        System.out.println("Terdapat Currency");
        Assert.assertFalse(currency.isEmpty(), "Currency Kosong");
        System.out.println("Currency Tidak Kosong");
        Assert.assertTrue(currency.matches("^[A-Z]{3}$"), "Currency tidak dalam format ISO 4217 (3 huruf kapital)");
        Assert.assertEquals(currency, expectedCurrency, "Currency Tidak Sesuai Expected");
        System.out.println("Currency Sudah Sesuai");

    }

    public static void dspSign(Body body, String jwt) {
        Allure.step("Check dsp sign");
        String dspSign = body.getAdditionalInfo().getDspsign();
        Assert.assertNotNull(dspSign, "dsp sign Tidak ada");
        System.out.println("Terdapat dsp sign");
        Assert.assertFalse(dspSign.isEmpty(), "dsp sign Kosong");
        System.out.println("dsp sign Tidak Kosong");
        Assert.assertEquals(dspSign, "Bearer " + jwt, "dsp sign Tidak Sesuai Expected");
        System.out.println("dspSign  Sudah Sesuai");

    }

    public static void disbCategory(Body body, String expectedCategory) {
        Allure.step("Check Disbursement Category");
        String disbCategory = body.getAdditionalInfo().getDisbCategory();
        Assert.assertNotNull(disbCategory, "Disbursement Category Tidak ada");
        System.out.println("Terdapat Disbursement Category");
        Assert.assertFalse(disbCategory.isEmpty(), "Disbursement Category Kosong");
        System.out.println("Disbursement Category Tidak Kosong");

        Set<String> validDisbCategory = Set.of(
                "DISBURSEMENT", "ECOMMERCE", "LOAN", "FUNDTRANSFER", "TOPUP", "LOAN", "ESCROWACCT");

        if (validDisbCategory.contains(disbCategory)) {
            Assert.fail("Account Type tidak ada dalam spec: " + disbCategory);
        }

        Assert.assertEquals(disbCategory, expectedCategory, "Disbursement Category Tidak Sesuai Expected");
        System.out.println("Disbursement Category Sudah Sesuai");

    }

    public static void senderName(Body body) {
        Allure.step("Check Sender Name");
        String senderName = body.getAdditionalInfo().getSenderInfo().getName();
        Assert.assertNotNull(senderName, "Sender Name Tidak ada");
        System.out.println("Terdapat Sender Name");
        Assert.assertFalse(senderName.isEmpty(), "Sender Name Kosong");
        System.out.println("Sender Name Tidak Kosong");
        System.out.println("Sender Name  Sudah Sesuai");

    }

    public static void accType(Body body) {
        Allure.step("Check Account Type");
        String accType = body.getAdditionalInfo().getSenderInfo().getAccountType();
        Assert.assertNotNull(accType, "Account Type Tidak ada");
        System.out.println("Terdapat Account Type");
        Assert.assertFalse(accType.isEmpty(), "Account Type Kosong");
        System.out.println("Account Type Tidak Kosong");

        Set<String> validAccTypes = Set.of(
                "CASH", "SAVING", "CHECKING", "CREDITCARD", "EMONEY", "LOAN", "ESCROWACCT");

        if (!validAccTypes.contains(accType)) {
            Assert.fail("Account Type tidak ada dalam spec: " + accType);
        }

        System.out.println("Account Type Sudah Sesuai");

    }

    public static void accInstId(Body body) {
        Allure.step("Check Account Institution Id");
        String accInstId = body.getAdditionalInfo().getSenderInfo().getAccountInstId();
        Assert.assertNotNull(accInstId, "Account Institution Id Tidak ada");
        System.out.println("Terdapat Account Institution Id");
        Assert.assertFalse(accInstId.isEmpty(), "Account Institution Id Kosong");
        System.out.println("Account Institution Id Tidak Kosong");

        System.out.println("Account Institution Id Sudah Sesuai");

    }

    public static void country(Body body) {
        Allure.step("Check Country");
        String country = body.getAdditionalInfo().getSenderInfo().getCountry();
        Assert.assertNotNull(country, "Country Tidak ada");
        System.out.println("Terdapat Country");
        Assert.assertFalse(country.isEmpty(), "Country Kosong");
        System.out.println("Country Tidak Kosong");

        Set<String> validCountryCode = Set.of(
                "AFG", "ALA", "ALB", "DZA", "ASM", "AND", "AGO", "AIA", "ATA", "ATG", "ARG", "ARM", "ABW", "AUS", "AUT",
                "AZE",
                "BHS", "BHR", "BGD", "BRB", "BLR", "BEL", "BLZ", "BEN", "BMU", "BTN", "BOL", "BES", "BIH", "BWA", "BVT",
                "BRA",
                "IOT", "BRN", "BGR", "BFA", "BDI", "CPV", "KHM", "CMR", "CAN", "CYM", "CAF", "TCD", "CHL", "CHN", "CXR",
                "CCK",
                "COL", "COM", "COG", "COD", "COK", "CRI", "CIV", "HRV", "CUB", "CUW", "CYP", "CZE", "DNK", "DJI", "DMA",
                "DOM",
                "ECU", "EGY", "SLV", "GNQ", "ERI", "EST", "SWZ", "ETH", "FLK", "FRO", "FJI", "FIN", "FRA", "GUF", "PYF",
                "ATF",
                "GAB", "GMB", "GEO", "DEU", "GHA", "GIB", "GRC", "GRL", "GRD", "GLP", "GUM", "GTM", "GGY", "GIN", "GNB",
                "GUY",
                "HTI", "HMD", "VAT", "HND", "HKG", "HUN", "ISL", "IND", "IDN", "IRN", "IRQ", "IRL", "IMN", "ISR", "ITA",
                "JAM",
                "JPN", "JEY", "JOR", "KAZ", "KEN", "KIR", "PRK", "KOR", "KWT", "KGZ", "LAO", "LVA", "LBN", "LSO", "LBR",
                "LBY",
                "LIE", "LTU", "LUX", "MAC", "MDG", "MWI", "MYS", "MDV", "MLI", "MLT", "MHL", "MTQ", "MRT", "MUS", "MYT",
                "MEX",
                "FSM", "MDA", "MCO", "MNG", "MNE", "MSR", "MAR", "MOZ", "MMR", "NAM", "NRU", "NPL", "NLD", "NCL", "NZL",
                "NIC",
                "NER", "NGA", "NIU", "NFK", "MNP", "NOR", "OMN", "PAK", "PLW", "PSE", "PAN", "PNG", "PRY", "PER", "PHL",
                "PCN",
                "POL", "PRT", "PRI", "QAT", "MKD", "ROU", "RUS", "RWA", "REU", "BLM", "SHN", "KNA", "LCA", "MAF", "SPM",
                "VCT",
                "WSM", "SMR", "STP", "SAU", "SEN", "SRB", "SYC", "SLE", "SGP", "SXM", "SVK", "SVN", "SLB", "SOM", "ZAF",
                "SGS",
                "SSD", "ESP", "LKA", "SDN", "SUR", "SJM", "SWE", "CHE", "SYR", "TWN", "TJK", "TZA", "THA", "TLS", "TGO",
                "TKL",
                "TON", "TTO", "TUN", "TUR", "TKM", "TCA", "TUV", "UGA", "UKR", "ARE", "GBR", "USA", "UMI", "URY", "UZB",
                "VUT",
                "VEN", "VNM", "VGB", "VIR", "WLF", "ESH", "YEM", "ZMB", "ZWE");

        if (!validCountryCode.contains(country)) {
            Assert.fail("Account Type tidak ada dalam spec: " + country);
        }

        System.out.println("Country Sudah Sesuai");

    }

    public static void city(Body body) {
        Allure.step("Check City");
        String city = body.getAdditionalInfo().getSenderInfo().getCity();
        Assert.assertNotNull(city, "City Tidak ada");
        System.out.println("Terdapat City");
        Assert.assertFalse(city.isEmpty(), "City Kosong");
        System.out.println("City Tidak Kosong");

        System.out.println("City Sudah Sesuai");

    }

    public static void identificationType(Body body) {
        Allure.step("Check Identification Type");
        String idType = body.getAdditionalInfo().getSenderInfo().getIdentificationType();
        Assert.assertNotNull(idType, "Identification Type Tidak ada");
        System.out.println("Terdapat Identification Type");
        Assert.assertFalse(idType.isEmpty(), "Identification Type Kosong");
        System.out.println("Identification Type Tidak Kosong");

        Set<String> validAccTypes = Set.of("KTP", "PASSPORT");

        if (!validAccTypes.contains(idType)) {
            Assert.fail("Identification Type tidak ada dalam spec: " + idType);
        }

        System.out.println("Identification Type Sudah Sesuai");

    }

    public static void identificationNo(Body body) {
        Allure.step("Check Id Number");
        String idNumber = body.getAdditionalInfo().getSenderInfo().getIdentificationNumber();
        Assert.assertNotNull(idNumber, "Id Number Tidak ada");
        System.out.println("Terdapat Id Number");
        Assert.assertFalse(idNumber.isEmpty(), "Id Number Kosong");
        System.out.println("Id Number Tidak Kosong");

        System.out.println("Id Number Sudah Sesuai");

    }

    public static void serviceCode(Body body) {
        Allure.step("Check Service Code");
        String serviceCode = body.getServiceCode();
        Assert.assertNotNull(serviceCode, "Service Code Tidak ada");
        System.out.println("Terdapat Service Code");
        Assert.assertFalse(serviceCode.isEmpty(), "Service Code Kosong");
        System.out.println("Service Code Tidak Kosong");

        Assert.assertEquals(serviceCode, "18", "Service Code Tidak Sesuai Expected");

        System.out.println("Service Code Sudah Sesuai");

    }

    public static void msgId(Body body) {
        Allure.step("Check MSG ID");
        String msgId = body.getAdditionalInfo().getMsgId();
        Assert.assertNotNull(msgId, "MSG ID Tidak ada");
        System.out.println("Terdapat MSG ID");
        Assert.assertFalse(msgId.isEmpty(), "MSG ID Kosong");
        System.out.println("MSG ID Tidak Kosong");
        Assert.assertTrue(msgId.length() <= 42, "Jumlah MSG ID lebih dari 42");
        System.out.println("MSG ID Sudah Kurang Dari 42");
        System.out.println("MSG ID Sudah Sesuai");

    }

    public static void accNo(Body body, String expectedPartnerId) {
        Allure.step("Check Account No");
        String accNo = body.getAccountNo();
        Assert.assertNotNull(accNo, "Account No Tidak ada");
        System.out.println("Terdapat Account No");
        Assert.assertFalse(accNo.isEmpty(), "Account No Kosong");
        System.out.println("Account No Tidak Kosong");

        Assert.assertEquals(accNo, expectedPartnerId, "Account No Tidak Sesuai Expected");

        System.out.println("Account No Sudah Sesuai");

    }

    public static void checkMissingMandatoryFields(String jsonStringHeader,
            Set<String> mandatoryFieldsheader, String jsonStringBody, Set<String> mandatoryFieldsBody) {
        Allure.step("Check Missing Mandatory Field");
        JSONObject json1 = new JSONObject(jsonStringHeader);
        JSONObject json2 = new JSONObject(jsonStringBody);

        boolean isMissingFieldJsonHeader = false;
        boolean isMissingFieldJsonBody = false;

        for (String field : mandatoryFieldsheader) {
            if (!jsonPathExists(json1, field)) {
                System.out.println("Missing mandatory field in JSONHeader: " + field);
                isMissingFieldJsonHeader = true;
            }
        }

        for (String field : mandatoryFieldsBody) {
            if (!jsonPathExists(json2, field)) {
                System.out.println("Missing mandatory field in JSONBody: " + field);
                isMissingFieldJsonBody = true;
            }
        }

        if (isMissingFieldJsonHeader || isMissingFieldJsonBody) {
            Assert.assertTrue(true, "Test berhasil karena ada missing mandatory field di salah satu JSON.");
        } else {
            Assert.fail("Tidak ada missing mandatory fields, kedua JSON lengkap.");
        }
    }

    public static boolean jsonPathExists(JSONObject json, String fieldPath) {
        String[] keys = fieldPath.split("\\."); // Memecah path berdasarkan titik (dot notation)
        JSONObject currentJson = json;

        // Menavigasi ke objek bersarang menggunakan keys yang dipecah
        for (int i = 0; i < keys.length; i++) {
            if (currentJson.has(keys[i])) {
                // Jika ini adalah key terakhir, cek apakah itu valid
                if (i == keys.length - 1) {
                    return true;
                } else {
                    currentJson = currentJson.getJSONObject(keys[i]);
                }
            } else {
                return false; // Jika key tidak ditemukan
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Contoh JSON string yang akan diperiksa
        // String jsonString = "{\r\n" + //
        // " \"partnerReferenceNo\": \"24062011140563260278\",\r\n" + //
        // " \"beneficiaryBankCode\": \"BCA\",\r\n" + //
        // " \"beneficiaryAccountNo\": \"1234567890\",\r\n" + //
        // " \"additionalInfo\": {\r\n" + //
        // " \"transferService\": \"FAST\",\r\n" + //
        // " \"amount\": {\r\n" + //
        // " \"value\": \"15001.00\",\r\n" + //
        // " \"currency\": \"IDR\"\r\n" + //
        // " }\r\n" + //
        // " },\r\n" + //
        // " \"dspsign\": \"sampleSignatureString1234567890\"\r\n" + //
        // "}";

        // Set of mandatory fields
        // Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo",
        // "beneficiaryBankCode", "beneficiaryAccountNo",
        // "additionalInfo.transferService", "additionalInfo.amount.value",
        // "additionalInfo.amount.currency",
        // "dspsign");

        // Memanggil method untuk memeriksa apakah ada field yang hilang
        // checkMissingMandatoryFields(jsonString, mandatoryFieldsBody);
    }

}
