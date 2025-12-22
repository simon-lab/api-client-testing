package com.saimen.AssertionMethod;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saimen.api.dto.ValidationContext;
import com.saimen.api.dto.ValidationResult;
import com.saimen.api.entity.Body;
import com.saimen.api.entity.Header;
import com.saimen.api.entity.Url;

public class assertionRequest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void assertUrl(Url urlEndpoint, ValidationContext ctx, String expectedServices) {
        String url = urlEndpoint.getUrlEndpoint();
        String inquiryUrl = "https://developer-uat.dspratama.co.id:9065/disbursement/v2.0/account-inquiry-external";
        String exeUrl = "https://developer-uat.dspratama.co.id:9065/disbursement/v2.0/transfer-interbank";
        String statusUrl = "https://developer-uat.dspratama.co.id:9065/disbursement/v2.0/transfer/status";
        String balanceUrl = "https://developer-uat.dspratama.co.id:9065/disbursement/v2.0/balance-inquiry";

        if (url == null) {
            ctx.addError("Url Endpoint tidak ada");
        } else if (url.isEmpty()) {
            ctx.addError("Url Endpoint kosong");
        } else if (expectedServices.equalsIgnoreCase("inquiry")) {
            if (!url.equals(inquiryUrl)) {
                ctx.addError("Url Endpoint Salah");
            }
        } else if (expectedServices.equalsIgnoreCase("execution")) {
            if (!url.equals(exeUrl)) {
                ctx.addError("Url Endpoint Salah");
            }
        } else if (expectedServices.equalsIgnoreCase("status")) {
            if (!url.equals(statusUrl)) {
                ctx.addError("Url Endpoint Salah");
            }
        } else if (expectedServices.equalsIgnoreCase("balance")) {
            if (!url.equals(balanceUrl)) {
                ctx.addError("Url Endpoint Salah");
            }
        }

    }

    public static void assertXTimeStamp(Header head, ValidationContext ctx) {
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
        String xExternalId = head.getxExternalId();

        if (xExternalId == null) {
            ctx.addError("External ID Tidak ada");
        } else if (xExternalId.isEmpty()) {
            ctx.addError("External ID kosong");
        }
    }

    public static void assertChannelID(Header head, String expectedChannelID, ValidationContext ctx) {
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
        String sourceAccountNo = body.getSourceAccountNo();

        if (sourceAccountNo == null) {
            ctx.addError("Partner Reference No Tidak ada");
        } else if (sourceAccountNo.isEmpty()) {
            ctx.addError("Partner Reference No kosong");
        } else if (sourceAccountNo.length() >= 19) {
            ctx.addError("Jumlah karakter partnerReferenceNo lebih dari 64");
        }

    }

    public static void beneBankCodeInq(Body body, ValidationContext ctx) {

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Transfer Service Tidak ada");
            return;
        }

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

    public static void beneBankCodeExe(Body body, String expectedTransferService, ValidationContext ctx) {

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Transfer Service Tidak ada");
            return;
        }

        String beneBankCode = body.getBeneficiaryBankCode();
        String transferService = expectedTransferService;

        if (beneBankCode == null) {
            ctx.addError("Bene Bank Code Tidak ada");
        } else if (beneBankCode.isEmpty()) {
            ctx.addError("Bene Bank Code kosong");
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
        String beneAccName = body.getBeneficiaryAccountName();

        if (beneAccName == null) {
            ctx.addError("Beneficiary Account No Tidak ada");
        } else if (beneAccName.isEmpty()) {
            ctx.addError("Beneficiary Account No kosong");
        }

    }

    public static void trxDate(Body body, ValidationContext ctx) {
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

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Transfer Service Tidak ada");
            return;
        }

        String transferService = body.getAdditionalInfo().getTransferService();

        if (transferService == null) {
            ctx.addError("Transfer Service Tidak ada");
        } else if (transferService.isEmpty()) {
            ctx.addError("Transfer Service kosong");
        }

    }

    public static void value(Body body, ValidationContext ctx) {

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Value Tidak Ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getAmount();

        if (ai1 == null) {
            ctx.addError("Value Tidak Ada");
            return;
        }
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

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Currency Tidak Ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getAmount();

        if (ai1 == null) {
            ctx.addError("Currency Tidak Ada");
            return;
        }

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

        var ai = body.getAmount();
        if (ai == null) {
            ctx.addError("Value Tidak ada");
            return;
        }
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
        var ai = body.getAmount();
        if (ai == null) {
            ctx.addError("Currency Tidak ada");
            return;
        }
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

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("dsp sign Tidak ada");
            return;
        }

        String dspSign = body.getAdditionalInfo().getDspsign();

        if (dspSign == null) {
            ctx.addError("dsp sign Tidak ada");
        } else if (dspSign.isEmpty()) {
            ctx.addError("dsp sign kosong");
        } else if (!dspSign.equals("Bearer " + jwt)) {
            ctx.addError("dsp sign jwt Tidak Sesuai Expected");
        }

    }

    public static void disbCategory(Body body, String expectedCategory, ValidationContext ctx) {

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Disbursement Category Tidak ada");
            return;
        }
        String disbCategory = body.getAdditionalInfo().getDisbCategory();

        Set<String> validDisbCategory = Set.of(
                "DISBURSEMENT", "ECOMMERCE", "LOAN", "FUNDTRANSFER", "TOPUP", "LOAN", "ESCROWACCT");

        if (disbCategory == null) {
            ctx.addError("Disbursement Category Tidak ada");
        } else if (disbCategory.isEmpty()) {
            ctx.addError("Disbursement Category kosong");
        } else if (!validDisbCategory.contains(disbCategory)) {
            ctx.addError("Disbursement Category tidak ada dalam spec: " + disbCategory);
        } else if (!disbCategory.equals(expectedCategory)) {
            ctx.addError("Disbursement Category Tidak Sesuai Expected");
        }

    }

    public static void senderName(Body body, ValidationContext ctx) {

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Sender Name Tidak ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getSenderInfo();

        if (ai1 == null) {
            ctx.addError("Sender Name Tidak ada");
            return;
        }

        String senderName = body.getAdditionalInfo().getSenderInfo().getName();

        if (senderName == null) {
            ctx.addError("Sender Name Tidak ada");
        } else if (senderName.isEmpty()) {
            ctx.addError("Sender Name kosong");
        }

    }

    public static void accType(Body body, ValidationContext ctx) {
        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Account Type Tidak ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getSenderInfo();

        if (ai1 == null) {
            ctx.addError("Account Type Tidak ada");
            return;
        }

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
        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Account Institution Id Tidak ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getSenderInfo();

        if (ai1 == null) {
            ctx.addError("Account Institution Id Tidak ada");
            return;
        }

        String accInstId = body.getAdditionalInfo().getSenderInfo().getAccountInstId();

        if (accInstId == null) {
            ctx.addError("Account Institution Id Tidak ada");
        } else if (accInstId.isEmpty()) {
            ctx.addError("Account Institution Id kosong");
        }

    }

    public static void country(Body body, ValidationContext ctx) {
        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Country Tidak ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getSenderInfo();

        if (ai1 == null) {
            ctx.addError("Country Tidak ada");
            return;
        }

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

        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("City Tidak ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getSenderInfo();

        if (ai1 == null) {
            ctx.addError("City Tidak ada");
            return;
        }

        String city = body.getAdditionalInfo().getSenderInfo().getCity();

        if (city == null) {
            ctx.addError("City Tidak ada");
        } else if (city.isEmpty()) {
            ctx.addError("City kosong");
        }

    }

    public static void identificationType(Body body, ValidationContext ctx) {
        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Identification Type Tidak ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getSenderInfo();

        if (ai1 == null) {
            ctx.addError("Identification Type Tidak ada");
            return;
        }

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
        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("Identification Number Tidak ada");
            return;
        }

        var ai1 = body.getAdditionalInfo().getSenderInfo();

        if (ai1 == null) {
            ctx.addError("Identification Number Tidak ada");
            return;
        }

        String idNumber = body.getAdditionalInfo().getSenderInfo().getIdentificationNumber();

        if (idNumber == null) {
            ctx.addError("Identification Number Tidak ada");
        } else if (idNumber.isEmpty()) {
            ctx.addError("Identification Number kosong");
        }

    }

    public static void serviceCode(Body body, ValidationContext ctx) {
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
        var ai = body.getAdditionalInfo();
        if (ai == null) {
            ctx.addError("MSG ID Tidak ada");
            return;
        }
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
        String accNo = body.getAccountNo();

        if (accNo == null) {
            ctx.addError("Account No Tidak ada");
        } else if (accNo.isEmpty()) {
            ctx.addError("Account No kosong");
        } else if (!accNo.equals(expectedPartnerId)) {
            ctx.addError("Account No Tidak Sesuai Expected");
        }

    }

    public static ValidationResult checkMissingHeaderMandatoryFields(Object bodyEntity,
            Set<String> mandatoryFields,
            ValidationContext ctx) {
        if (bodyEntity == null) {
            String msg = "Body entity null (seluruh mandatory dianggap missing)";
            if (ctx != null)
                ctx.addSuccess(msg);
            return new ValidationResult("OK", msg);
        }

        JsonNode root = MAPPER.valueToTree(bodyEntity);

        Set<String> missing = new HashSet<>();
        for (String field : mandatoryFields) {
            JsonNode val = root.get(field);
            if (val == null || val.isNull() || (val.isTextual() && val.asText().isBlank())) {
                missing.add(field);
            }
        }

        if (!missing.isEmpty()) {
            String msg = "Header missing: " + String.join(", ", missing);
            if (ctx != null)
                ctx.addSuccess(msg);
            return new ValidationResult("OK", msg);
        } else {
            String msg = "Tidak ada yang missing";
            if (ctx != null)
                ctx.addError(msg);
            return new ValidationResult("ERROR", msg);
        }
    }

    public static ValidationResult checkMissingBodyMandatoryFields(Object bodyEntity,
            Set<String> mandatoryFields,
            ValidationContext ctx) {
        if (bodyEntity == null) {
            String msg = "Body entity null (seluruh mandatory dianggap missing)";
            if (ctx != null)
                ctx.addSuccess(msg);
            return new ValidationResult("OK", msg);
        }

        JsonNode root = MAPPER.valueToTree(bodyEntity);

        Set<String> missing = new HashSet<>();
        for (String path : mandatoryFields) {
            JsonNode node = getByPath(root, path);
            if (isMissing(node)) {
                missing.add(path);
            }
        }

        if (!missing.isEmpty()) {
            String msg = "Body missing: " + String.join(", ", missing);
            if (ctx != null)
                ctx.addSuccess(msg);
            return new ValidationResult("OK", msg);
        } else {
            String msg = "Tidak ada yang missing";
            if (ctx != null)
                ctx.addError(msg);
            return new ValidationResult("ERROR", msg);
        }
    }

    private static JsonNode getByPath(JsonNode root, String dotPath) {
        if (root == null || dotPath == null || dotPath.isBlank())
            return null;
        String[] segments = dotPath.split("\\.");

        JsonNode curr = root;
        for (String seg : segments) {
            if (curr == null)
                return null;

            int bracketIdx = seg.indexOf('[');
            if (bracketIdx >= 0) {
                String key = seg.substring(0, bracketIdx);
                curr = curr.get(key);
                if (curr == null)
                    return null;

                int pos = bracketIdx;
                while (pos < seg.length() && seg.charAt(pos) == '[') {
                    int end = seg.indexOf(']', pos);
                    if (end < 0)
                        return null;
                    String numStr = seg.substring(pos + 1, end).trim();
                    int idx;
                    try {
                        idx = Integer.parseInt(numStr);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                    if (!curr.isArray() || idx < 0 || idx >= curr.size())
                        return null;
                    curr = curr.get(idx);
                    pos = end + 1;
                }
            } else {
                curr = curr.get(seg);
            }
        }
        return curr;
    }

    private static boolean isMissing(JsonNode node) {
        if (node == null || node.isNull())
            return true;
        if (node.isTextual() && node.asText().isBlank())
            return true;
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
