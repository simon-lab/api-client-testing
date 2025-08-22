package com.saimen.ReusableMethod;

import java.util.Set;

import org.json.JSONObject;
import org.testng.Assert;

import com.saimen.api.dto.ValidationContext;
import com.saimen.api.entity.Body;
import com.saimen.api.entity.Header;

import io.qameta.allure.Allure;

public class assertionRequest {

    public static void assertXTimeStamp(Header head, ValidationContext ctx) {
        Allure.step("Check X-Time-Stamp");
        String timestamp = head.getxTimeStamp();
        String pattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2})$";

        if (timestamp == null) {
            ctx.addError("X-TIME-STAMP Tidak ada");
        } else if (timestamp.isEmpty()) {
            ctx.addError("X-TIME-STAMP kosong");
        } else if (!timestamp.matches(pattern)) {
            ctx.addError("Salah Format X-TIME-STAMP");
        }
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

    public static void assertXPartnerId(Header head, String partnerID, ValidationContext ctx) {
        Allure.step("Check Partner ID");
        String xPartnerId = head.getxPartnerId();

        if (xPartnerId == null) {
            ctx.addError("X-PARTNER-ID Tidak ada");
        } else if (xPartnerId.isEmpty()) {
            ctx.addError("X-PARTNER-ID kosong");
        } else if (!xPartnerId.equals(partnerID)) {
            ctx.addError("X-PARTNER-ID Tidak Sesuai Expected");
        }
    }

    public static void assertXExternalId(Header head, ValidationContext ctx) {
        Allure.step("Check External ID");
        String xExternalId = head.getxExternalId();

        if (xExternalId == null) {
            ctx.addError("External ID Tidak ada");
        } else if (xExternalId.isEmpty()) {
            ctx.addError("External ID kosong");
        }
    }

    public static void assertChannelID(Header head, String expectedChannelID, ValidationContext ctx) {
        Allure.step("Check Channel-ID");
        String channelId = head.getChannelId();

        if (channelId == null) {
            ctx.addError("Channel-ID Tidak ada");
        } else if (channelId.isEmpty()) {
            ctx.addError("Channel-ID kosong");
        } else if (!channelId.equals(expectedChannelID)) {
            ctx.addError("Channel-ID Tidak Sesuai Expected");
        }

    }

    public static void assertPartnerReferenceNo(Body body, ValidationContext ctx) {
        Allure.step("Check Partner Reference No");
        String partnerReferenceNo = body.getPartnerReferenceNo();

        if (partnerReferenceNo == null) {
            ctx.addError("Partner Reference No Tidak ada");
        } else if (partnerReferenceNo.isEmpty()) {
            ctx.addError("Partner Reference No kosong");
        } else if (partnerReferenceNo.length() >= 64) {
            ctx.addError("Jumlah karakter partnerReferenceNo lebih dari 64");
        }

    }

    public static void assertOriPartnerReferenceNo(Body body, ValidationContext ctx) {
        Allure.step("Check Partner Reference No");
        String partnerReferenceNo = body.getOriginalPartnerReferenceNo();

        if (partnerReferenceNo == null) {
            ctx.addError("Original Partner Reference No Tidak ada");
        } else if (partnerReferenceNo.isEmpty()) {
            ctx.addError("Original Partner Reference No kosong");
        } else if (partnerReferenceNo.length() >= 64) {
            ctx.addError("Jumlah Original karakter partnerReferenceNo lebih dari 64");
        }

    }

    public static void sourceAccountNo(Body body, ValidationContext ctx) {
        Allure.step("Check Source Account No");
        String sourceAccountNo = body.getSourceAccountNo();

        if (sourceAccountNo == null) {
            ctx.addError("Partner Reference No Tidak ada");
        } else if (sourceAccountNo.isEmpty()) {
            ctx.addError("Partner Reference No kosong");
        } else if (sourceAccountNo.length() >= 19) {
            ctx.addError("Jumlah karakter partnerReferenceNo lebih dari 64");
        }

    }

    public static void beneBankCode(Body body, ValidationContext ctx) {
        Allure.step("Check Beneficiary Bank Code");
        String beneBankCode = body.getBeneficiaryBankCode();
        String transferService = body.getAdditionalInfo().getTransferService();

        if (beneBankCode == null) {
            ctx.addError("Bene Bank Code Tidak ada");
        } else if (beneBankCode.isEmpty()) {
            ctx.addError("Bene Bank Code kosong");
        } else if (transferService == null) {
            ctx.addError("Transfer Service Tidak ada");
        } else if (transferService.isEmpty()) {
            ctx.addError("Transfer Service kosong");
        } else if (transferService.equalsIgnoreCase("REALTIME ONLINE")) {
            if (beneBankCode.length() > 3) {
                ctx.addError("Jumlah karakter Beneficiary Bank Code REALTIME ONLINE lebih dari 3");
            }
        } else if (transferService.equalsIgnoreCase("BI FAST")) {
            if (beneBankCode.length() > 8) {
                ctx.addError("Jumlah karakter Beneficiary Bank Code BI FAST lebih dari 8");
            }
        } else {
            ctx.addError("Transfer service tidak dikenali");
        }

    }

    public static void beneAccNo(Body body, ValidationContext ctx) {
        Allure.step("Check Beneficiary Account No");
        String beneAccNo = body.getBeneficiaryAccountNo();

        if (beneAccNo == null) {
            ctx.addError("Beneficiary Account No Tidak ada");
        } else if (beneAccNo.isEmpty()) {
            ctx.addError("Beneficiary Account No kosong");
        } else if (beneAccNo.length() >= 19) {
            ctx.addError("Jumlah karakter Beneficiary Account No lebih dari 19");
        }

    }

    public static void beneAccName(Body body, ValidationContext ctx) {
        Allure.step("Check Beneficiary Account Name");
        String beneAccName = body.getBeneficiaryAccountName();

        if (beneAccName == null) {
            ctx.addError("Beneficiary Account No Tidak ada");
        } else if (beneAccName.isEmpty()) {
            ctx.addError("Beneficiary Account No kosong");
        }

    }

    public static void trxDate(Body body, ValidationContext ctx) {
        Allure.step("Check Transaction Date");
        String trxDate = body.getTransactionDate();
        String pattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2}$";

        if (trxDate == null) {
            ctx.addError("Transaction Date Tidak ada");
        } else if (trxDate.isEmpty()) {
            ctx.addError("Transaction Date kosong");
        } else if (!trxDate.matches(pattern)) {
            ctx.addError("Format tidak valid! Harus YYYY-MM-DDTHH:mm:ss+HH:mm");
        }

    }

    public static void transferService(Body body, String expectedTransferService, ValidationContext ctx) {
        Allure.step("Check Transfer Service");
        String transferService = body.getAdditionalInfo().getTransferService();

        if (transferService == null) {
            ctx.addError("Transfer Service Tidak ada");
        } else if (transferService.isEmpty()) {
            ctx.addError("Transfer Service kosong");
        }

    }

    public static void value(Body body, ValidationContext ctx) {
        Allure.step("Check Value");
        String value = body.getAdditionalInfo().getAmount().getValue();
        String pattern = "^\\d{1,16}\\.\\d{2}$";

        if (value == null) {
            ctx.addError("Value Tidak ada");
        } else if (value.isEmpty()) {
            ctx.addError("Value kosong");
        } else if (!value.matches(pattern)) {
            ctx.addError("Format Value tidak valid! Harus max 16 digit di depan, dan 2 di belakang koma.");
        }

    }

    public static void currency(Body body, String expectedCurrency, ValidationContext ctx) {
        Allure.step("Check Currency");
        String currency = body.getAdditionalInfo().getAmount().getCurrency();

        if (currency == null) {
            ctx.addError("Currency Tidak ada");
        } else if (currency.isEmpty()) {
            ctx.addError("Currency kosong");
        } else if (!currency.matches("^[A-Z]{3}$")) {
            ctx.addError("Currency tidak dalam format ISO 4217 (3 huruf kapital)");
        }

    }

    public static void valueExe(Body body, ValidationContext ctx) {
        Allure.step("Check Value");
        String value = body.getAmount().getValue();
        String pattern = "^\\d{1,16}\\.\\d{2}$";

        if (value == null) {
            ctx.addError("Value Tidak ada");
        } else if (value.isEmpty()) {
            ctx.addError("Value kosong");
        } else if (!value.matches(pattern)) {
            ctx.addError("Format Value tidak valid! Harus max 16 digit di depan, dan 2 di belakang koma.");
        }

    }

    public static void currencyExe(Body body, String expectedCurrency, ValidationContext ctx) {
        Allure.step("Check Currency");
        String currency = body.getAmount().getCurrency();

        if (currency == null) {
            ctx.addError("Currency Tidak ada");
        } else if (currency.isEmpty()) {
            ctx.addError("Currency kosong");
        } else if (!currency.matches("^[A-Z]{3}$")) {
            ctx.addError("Currency tidak dalam format ISO 4217 (3 huruf kapital)");
        }

    }

    public static void dspSign(Body body, String jwt, ValidationContext ctx) {
        Allure.step("Check dsp sign");
        String dspSign = body.getAdditionalInfo().getDspsign();

        if (dspSign == null) {
            ctx.addError("Beneficiary Account No Tidak ada");
        } else if (dspSign.isEmpty()) {
            ctx.addError("Beneficiary Account No kosong");
        } else if (!dspSign.equals("Bearer " + jwt)) {
            ctx.addError("dsp sign jwt Tidak Sesuai Expected");
        }

    }

    public static void disbCategory(Body body, String expectedCategory, ValidationContext ctx) {
        Allure.step("Check Disbursement Category");
        String disbCategory = body.getAdditionalInfo().getDisbCategory();

        Set<String> validDisbCategory = Set.of(
                "DISBURSEMENT", "ECOMMERCE", "LOAN", "FUNDTRANSFER", "TOPUP", "LOAN", "ESCROWACCT");

        if (disbCategory == null) {
            ctx.addError("Disbursement Category Tidak ada");
        } else if (disbCategory.isEmpty()) {
            ctx.addError("Disbursement Category kosong");
        } else if (!validDisbCategory.contains(disbCategory)) {
            ctx.addError("Account Type tidak ada dalam spec: " + disbCategory);
        } else if (!disbCategory.equals(expectedCategory)) {
            ctx.addError("Disbursement Category Tidak Sesuai Expected");
        }

    }

    public static void senderName(Body body, ValidationContext ctx) {
        Allure.step("Check Sender Name");
        String senderName = body.getAdditionalInfo().getSenderInfo().getName();

        if (senderName == null) {
            ctx.addError("Sender Name Tidak ada");
        } else if (senderName.isEmpty()) {
            ctx.addError("Sender Name kosong");
        }

    }

    public static void accType(Body body, ValidationContext ctx) {
        Allure.step("Check Account Type");
        String accType = body.getAdditionalInfo().getSenderInfo().getAccountType();

        Set<String> validAccTypes = Set.of(
                "CASH", "SAVING", "CHECKING", "CREDITCARD", "EMONEY", "LOAN", "ESCROWACCT");

        if (accType == null) {
            ctx.addError("Account Type Tidak ada");
        } else if (accType.isEmpty()) {
            ctx.addError("Account Type kosong");
        } else if (!validAccTypes.contains(accType)) {
            ctx.addError("Account Type tidak ada dalam spec: " + accType);
        }

    }

    public static void accInstId(Body body, ValidationContext ctx) {
        Allure.step("Check Account Institution Id");
        String accInstId = body.getAdditionalInfo().getSenderInfo().getAccountInstId();

        if (accInstId == null) {
            ctx.addError("Account Institution Id Tidak ada");
        } else if (accInstId.isEmpty()) {
            ctx.addError("Account Institution Id kosong");
        }

    }

    public static void country(Body body, ValidationContext ctx) {
        Allure.step("Check Country");
        String country = body.getAdditionalInfo().getSenderInfo().getCountry();

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

        if (country == null) {
            ctx.addError("Country Tidak ada");
        } else if (country.isEmpty()) {
            ctx.addError("Country kosong");
        } else if (!validCountryCode.contains(country)) {
            ctx.addError("Country tidak ada dalam List: " + country);
        }

    }

    public static void city(Body body, ValidationContext ctx) {
        Allure.step("Check City");
        String city = body.getAdditionalInfo().getSenderInfo().getCity();

        if (city == null) {
            ctx.addError("City Tidak ada");
        } else if (city.isEmpty()) {
            ctx.addError("City kosong");
        }

    }

    public static void identificationType(Body body, ValidationContext ctx) {
        Allure.step("Check Identification Type");
        String idType = body.getAdditionalInfo().getSenderInfo().getIdentificationType();
        Set<String> validAccTypes = Set.of("KTP", "PASSPORT");

        if (idType == null) {
            ctx.addError("Identification Type Tidak ada");
        } else if (idType.isEmpty()) {
            ctx.addError("Identification Type kosong");
        } else if (!validAccTypes.contains(idType)) {
            ctx.addError("Identification Type tidak ada dalam spec: " + idType);
        }

    }

    public static void identificationNo(Body body, ValidationContext ctx) {
        Allure.step("Check Id Number");
        String idNumber = body.getAdditionalInfo().getSenderInfo().getIdentificationNumber();

        if (idNumber == null) {
            ctx.addError("Identification Number Tidak ada");
        } else if (idNumber.isEmpty()) {
            ctx.addError("Identification Number kosong");
        }

    }

    public static void serviceCode(Body body, ValidationContext ctx) {
        Allure.step("Check Service Code");
        String serviceCode = body.getServiceCode();

        if (serviceCode == null) {
            ctx.addError("Service Code Tidak ada");
        } else if (serviceCode.isEmpty()) {
            ctx.addError("Service Code kosong");
        } else if (!serviceCode.equals("18")) {
            ctx.addError("Service Code Tidak Sesuai Expected");
        }

    }

    public static void msgId(Body body, ValidationContext ctx) {
        Allure.step("Check MSG ID");
        String msgId = body.getAdditionalInfo().getMsgId();

        if (msgId == null) {
            ctx.addError("MSG ID Tidak ada");
        } else if (msgId.isEmpty()) {
            ctx.addError("MSG ID kosong");
        } else if (msgId.length() >= 42) {
            ctx.addError("Jumlah MSG ID lebih dari 42");
        }

    }

    public static void accNo(Body body, String expectedPartnerId, ValidationContext ctx) {
        Allure.step("Check Account No");
        String accNo = body.getAccountNo();

        if (accNo == null) {
            ctx.addError("Account No Tidak ada");
        } else if (accNo.isEmpty()) {
            ctx.addError("Account No kosong");
        } else if (!accNo.equals(expectedPartnerId)) {
            ctx.addError("Account No Tidak Sesuai Expected");
        }

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
