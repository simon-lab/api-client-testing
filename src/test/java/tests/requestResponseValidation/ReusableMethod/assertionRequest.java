package tests.requestResponseValidation.ReusableMethod;

import java.util.Set;
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

    public static void assertXPartnerId(JsonPath js, String partnerID) {
        Allure.step("Check Partner ID");
        String xPartnerId = js.getString("X-PARTNER-ID");
        Assert.assertNotNull(xPartnerId, "Partner ID Tidak ada");
        System.out.println("Terdapat Partner ID");
        Assert.assertFalse(xPartnerId.isEmpty(), "Partner ID Kosong");
        System.out.println("Partner ID Tidak Kosong");
        System.out.println("Partner ID: " + xPartnerId);
        System.out.println("Partner ID sudah sesuai format");
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

    public static void sourceAccountNo(JsonPath js, String expectedSourceAccountNo) {
        Allure.step("Check Source Account No");
        String sourceAccountNo = js.getString("sourceAccountNo");
        Assert.assertNotNull(sourceAccountNo, "Source Account No Tidak ada");
        System.out.println("Terdapat Source Account No");
        Assert.assertFalse(sourceAccountNo.isEmpty(), "Source Account No Kosong");
        System.out.println("Source Account No Tidak Kosong");
        Assert.assertTrue(sourceAccountNo.length() <= 19, "Jumlah karakter Source Account No lebih dari 19");
        Assert.assertEquals(sourceAccountNo, expectedSourceAccountNo,
                "Partner Reference No Tidak Sesuai Expected");
        System.out.println("Source Account No Sudah Sesuai");

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

    public static void beneAccName(JsonPath js, String expectedBeneAccName) {
        Allure.step("Check Beneficiary Account Name");
        String beneAccName = js.getString("beneficiaryAccountName");
        Assert.assertNotNull(beneAccName, "Beneficiary Account Name Tidak ada");
        System.out.println("Terdapat Beneficiary Account Name");
        Assert.assertFalse(beneAccName.isEmpty(), "Beneficiary Account Name Kosong");
        System.out.println("Beneficiary Account Name Tidak Kosong");
        Assert.assertEquals(beneAccName, expectedBeneAccName, "Beneficiary Account Name Tidak Sesuai Expected");
        System.out.println("Beneficiary Account Name Sudah Sesuai");

    }

    public static void trxDate(JsonPath js, String expectedTrxDate) {
        Allure.step("Check Transaction Date");
        String trxDate = js.getString("transactionDate");
        Assert.assertNotNull(trxDate, "Transaction Date Tidak ada");
        System.out.println("Terdapat Transaction Date");
        Assert.assertFalse(trxDate.isEmpty(), "Transaction Date Kosong");
        String pattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2}$";
        Assert.assertTrue(expectedTrxDate.matches(pattern), "Format tidak valid! Harus YYYY-MM-DDTHH:mm:ss+HH:mm");
        System.out.println("Transaction Date Tidak Kosong");
        Assert.assertEquals(trxDate, expectedTrxDate, "Transaction Date Tidak Sesuai Expected");
        System.out.println("Transaction Date Sudah Sesuai");

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

    public static void disbCategory(JsonPath js, String expectedCategory) {
        Allure.step("Check Disbursement Category");
        String disbCategory = js.getString("additionalInfo.disbCategory");
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

    public static void senderName(JsonPath js, String expectedSenderName) {
        Allure.step("Check Sender Name");
        String senderName = js.getString("senderInfo.name");
        Assert.assertNotNull(senderName, "Sender Name Tidak ada");
        System.out.println("Terdapat Sender Name");
        Assert.assertFalse(senderName.isEmpty(), "Sender Name Kosong");
        System.out.println("Sender Name Tidak Kosong");
        Assert.assertEquals(senderName, expectedSenderName, "Sender Name Tidak Sesuai Expected");
        System.out.println("Sender Name  Sudah Sesuai");

    }

    public static void accType(JsonPath js, String expectedAccType) {
        Allure.step("Check Account Type");
        String accType = js.getString("senderInfo.accountType");
        Assert.assertNotNull(accType, "Account Type Tidak ada");
        System.out.println("Terdapat Account Type");
        Assert.assertFalse(accType.isEmpty(), "Account Type Kosong");
        System.out.println("Account Type Tidak Kosong");

        Set<String> validAccTypes = Set.of(
                "CASH", "SAVING", "CHECKING", "CREDITCARD", "EMONEY", "LOAN", "ESCROWACCT");

        if (!validAccTypes.contains(accType)) {
            Assert.fail("Account Type tidak ada dalam spec: " + accType);
        }
        Assert.assertEquals(accType, expectedAccType, "Account Type Tidak Sesuai Expected");

        System.out.println("Account Type Sudah Sesuai");

    }

    public static void accInstId(JsonPath js, String expectedAccInstId) {
        Allure.step("Check Account Institution Id");
        String accInstId = js.getString("senderInfo.accountType");
        Assert.assertNotNull(accInstId, "Account Institution Id Tidak ada");
        System.out.println("Terdapat Account Institution Id");
        Assert.assertFalse(accInstId.isEmpty(), "Account Institution Id Kosong");
        System.out.println("Account Institution Id Tidak Kosong");

        Assert.assertEquals(accInstId, expectedAccInstId, "Account Institution Id Tidak Sesuai Expected");

        System.out.println("Account Institution Id Sudah Sesuai");

    }

    public static void country(JsonPath js, String expectedCountry) {
        Allure.step("Check Country");
        String country = js.getString("senderInfo.country");
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

        Assert.assertEquals(country, expectedCountry, "Country Tidak Sesuai Expected");

        System.out.println("Country Sudah Sesuai");

    }

    public static void city(JsonPath js, String expectedCity) {
        Allure.step("Check City");
        String city = js.getString("senderInfo.city");
        Assert.assertNotNull(city, "City Tidak ada");
        System.out.println("Terdapat City");
        Assert.assertFalse(city.isEmpty(), "City Kosong");
        System.out.println("City Tidak Kosong");

        Assert.assertEquals(city, expectedCity, "City Tidak Sesuai Expected");

        System.out.println("City Sudah Sesuai");

    }

    public static void identificType(JsonPath js, String expectedIdType) {
        Allure.step("Check Identification Type");
        String idType = js.getString("senderInfo.identificationType");
        Assert.assertNotNull(idType, "Identification Type Tidak ada");
        System.out.println("Terdapat Identification Type");
        Assert.assertFalse(idType.isEmpty(), "Identification Type Kosong");
        System.out.println("Identification Type Tidak Kosong");

        Set<String> validAccTypes = Set.of("KTP", "PASSPORT");

        if (!validAccTypes.contains(idType)) {
            Assert.fail("Identification Type tidak ada dalam spec: " + idType);
        }
        Assert.assertEquals(idType, expectedIdType, "Identification Type Tidak Sesuai Expected");

        System.out.println("Identification Type Sudah Sesuai");

    }

    public static void city(JsonPath js) {
        Allure.step("Check Id Number");
        String idNumber = js.getString("senderInfo.identificationNumber");
        Assert.assertNotNull(idNumber, "Id Number Tidak ada");
        System.out.println("Terdapat Id Number");
        Assert.assertFalse(idNumber.isEmpty(), "Id Number Kosong");
        System.out.println("Id Number Tidak Kosong");

        System.out.println("Id Number Sudah Sesuai");

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

    public static void msgId(JsonPath js, String expectedMsgId) {
        Allure.step("Check MSG ID");
        String msgId = js.getString("additionalInfo.msgId");
        Assert.assertNotNull(msgId, "MSG ID Tidak ada");
        System.out.println("Terdapat MSG ID");
        Assert.assertFalse(msgId.isEmpty(), "MSG ID Kosong");
        System.out.println("MSG ID Tidak Kosong");

        Assert.assertEquals(msgId, expectedMsgId, "MSG ID Tidak Sesuai Expected");

        System.out.println("MSG ID Sudah Sesuai");

    }

    public static void accNo(JsonPath js, String expectedPartnerId) {
        Allure.step("Check Account No");
        String accNo = js.getString("accountNo");
        Assert.assertNotNull(accNo, "Account No Tidak ada");
        System.out.println("Terdapat Account No");
        Assert.assertFalse(accNo.isEmpty(), "Account No Kosong");
        System.out.println("Account No Tidak Kosong");

        Assert.assertEquals(accNo, expectedPartnerId, "Account No Tidak Sesuai Expected");

        System.out.println("Account No Sudah Sesuai");

    }

}
