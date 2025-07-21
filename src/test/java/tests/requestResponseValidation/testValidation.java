package tests.requestResponseValidation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import tests.requestResponseValidation.ReusableMethod.assertionPackage;
import tests.requestResponseValidation.ReusableMethod.assertionRequest;
import tests.requestResponseValidation.ReusableMethod.assertionResponse;
import tests.requestResponseValidation.ReusableMethod.excelReader;
import tests.requestResponseValidation.ReusableMethod.methodIntro;
import tests.requestResponseValidation.ReusableMethod.parsingJson;
import tests.requestResponseValidation.ReusableMethod.separateCell;
import tests.requestResponseValidation.ReusableMethod.toRegex;
import tests.requestResponseValidation.model.clientTestData;
import util.JsonUtil;

public class testValidation {

    private clientTestData testData;
    String excelPath;
    excelReader excelReader = new excelReader();
    separateCell separate = new separateCell();
    parsingJson parsingJson = new parsingJson();
    methodIntro intro = new methodIntro();
    String separator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    @BeforeTest
    @Parameters({ "testDataPath" })
    public void setData(String testDataPath) {
        Allure.step("Set Excel");
        this.testData = JsonUtil.getTestData(testDataPath, clientTestData.class);
        excelPath = testData.excelPath();
        // List<String> expectedData = excelReader.expectedReader(excelPath);
        // List<String> requestData = excelReader.requestReader(excelPath);
        // List<String> responseData = excelReader.responseReader(excelPath);
        // System.out.println("INI EXPECTED RESULT");
        // System.out.println(expectedData);
        // System.out.println("INI REQUEST");
        // System.out.println(requestData);
        // System.out.println("INI RESPONSE");
        // System.out.println(responseData);
    }

    @BeforeMethod
    public void monitorMethod(Method method) {
        System.out.println(separator);
        System.out.println("Akan menjalankan test: " + method.getName());
    }

    // @Test
    public void case8o1() {

        List<String> expected = intro.expectedDefinition(excelPath, 1, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 1, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);
        System.out.println(formatRC);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);

    }

    // @Test
    public void case8o2() {

        List<String> expected = intro.expectedDefinition(excelPath, 2, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 2, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);
        System.out.println(formatRC);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    }

    @Test(priority = 3)
    public void case8o3() {

        String requestCase = excelReader.requestCell(excelPath, 3, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedDefinition(excelPath, 3, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 3, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        JSONObject jsonObjectBody = new JSONObject(bodyRequestString);
        Boolean inquiry = jsonObjectBody.has("additionalInfo.transferService");
        Boolean exe = jsonObjectBody.has("sourceAccountNo");
        Boolean checkStatus = jsonObjectBody.has("originalPartnerReferenceNo");
        Boolean getBalance = jsonObjectBody.has("accountNo");

        if (inquiry) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo",
                    "additionalInfo.transferService", "additionalInfo.amount.value", "additionalInfo.amount.currency",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (exe) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "sourceAccountNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo", "beneAccountName", "transactionDate", "amount.value", "amount.currency",
                    "additionalInfo.msgId", "additionalInfo.disbCategory", "senderInfo.name", "senderInfo.accountType",
                    "senderInfo,accountInstId", "senderInfo.country", "senderInfo.city",
                    "senderInfo.identificationType",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (checkStatus) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("originalPartnerReferenceNo", "serviceCode",
                    "additionalInfo.msgId", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (getBalance) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("accountNo", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else {
            System.out.print("Unique Field Tidak Ditemukan");
        }

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    }

    // @Test
    public void case8o4() {

        List<String> expected = intro.expectedDefinition(excelPath, 4, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 4, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);
        System.out.println(formatRC);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    }

    // @Test
    public void case8o5() {

        String requestCase = excelReader.requestCell(excelPath, 5, "interbank");
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedDefinition(excelPath, 5, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 5, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        JSONObject jsonObjectBody = new JSONObject(bodyRequestString);
        Boolean inquiry = jsonObjectBody.has("additionalInfo.transferService");
        Boolean exe = jsonObjectBody.has("additionalInfo.transferService");
        Boolean checkStatus = jsonObjectBody.has("originalPartnerReferenceNo");
        Boolean getBalance = jsonObjectBody.has("accountNo");

        if (inquiry) {
            assertionPackage.inquiry(jsHeader, jsBody, testData);
        } else if (exe) {
            assertionPackage.exe(jsHeader, jsBody, testData);

        } else if (checkStatus) {
            assertionPackage.checkStatus(jsHeader, jsBody, testData);

        } else if (getBalance) {
            assertionPackage.getBalance(jsHeader, jsBody, testData);

        } else {
            System.out.print("Unique Field Tidak Ditemukan");
        }

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    }

    // @Test
    public void case8o7() {

        List<String> expected = intro.expectedDefinition(excelPath, 7, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 7, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.inquiry(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccName(jsResponse);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertMsgId(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    // @Test
    public void case8o9() {

        List<String> expected = intro.expectedDefinition(excelPath, 8, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 8, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.inquiry(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccName(jsResponse);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertMsgId(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    // @Test
    public void case8o11() {

        List<String> expected = intro.expectedDefinition(excelPath, 10, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 10, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.exe(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");

    }

    // @Test
    public void case8o12() {

        List<String> expected = intro.expectedDefinition(excelPath, 11, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.exe(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    // @Test
    public void case8o13() {

        List<String> expected = intro.expectedDefinition(excelPath, 12, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 12, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.exe(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    // @Test
    public void case8o14() {

        List<String> expected = intro.expectedDefinition(excelPath, 13, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 13, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.checkStatus(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.serviceCode(jsResponse);
        assertionResponse.sourceAccountNo(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    // @Test
    public void case3o3() {

        String requestCase = excelReader.requestCell(excelPath, 3, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedDefinition(excelPath, 3, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 3, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        JSONObject jsonObjectBody = new JSONObject(bodyRequestString);
        Boolean inquiry = jsonObjectBody.has("additionalInfo.transferService");
        Boolean exe = jsonObjectBody.has("sourceAccountNo");
        Boolean checkStatus = jsonObjectBody.has("originalPartnerReferenceNo");
        Boolean getBalance = jsonObjectBody.has("accountNo");

        if (inquiry) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo",
                    "additionalInfo.transferService", "additionalInfo.amount.value", "additionalInfo.amount.currency",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (exe) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "sourceAccountNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo", "beneAccountName", "transactionDate", "amount.value", "amount.currency",
                    "additionalInfo.msgId", "additionalInfo.disbCategory", "senderInfo.name", "senderInfo.accountType",
                    "senderInfo,accountInstId", "senderInfo.country", "senderInfo.city",
                    "senderInfo.identificationType",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (checkStatus) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("originalPartnerReferenceNo", "serviceCode",
                    "additionalInfo.msgId", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (getBalance) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("accountNo", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else {
            System.out.print("Unique Field Tidak Ditemukan");
        }

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    }

    // @Test
    public void case3o4() {

        String requestCase = excelReader.requestCell(excelPath, 5, "balance");
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedDefinition(excelPath, 5, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 5, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        JSONObject jsonObjectBody = new JSONObject(bodyRequestString);
        Boolean inquiry = jsonObjectBody.has("additionalInfo.transferService");
        Boolean exe = jsonObjectBody.has("additionalInfo.transferService");
        Boolean checkStatus = jsonObjectBody.has("originalPartnerReferenceNo");
        Boolean getBalance = jsonObjectBody.has("accountNo");

        if (inquiry) {
            assertionPackage.inquiry(jsHeader, jsBody, testData);
        } else if (exe) {
            assertionPackage.exe(jsHeader, jsBody, testData);

        } else if (checkStatus) {
            assertionPackage.checkStatus(jsHeader, jsBody, testData);

        } else if (getBalance) {
            assertionPackage.getBalance(jsHeader, jsBody, testData);

        } else {
            System.out.print("Unique Field Tidak Ditemukan");
        }

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    }

    // @Test
    public void case3o5() {

        List<String> expected = intro.expectedDefinition(excelPath, 6, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 6, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.balanceType(jsResponse, "Disburse_Balance");
        assertionResponse.value(jsResponse);
        assertionResponse.currency(jsResponse, testData.currency());
    }

    // @Test
    public void case3o6() {

        List<String> expected = intro.expectedDefinition(excelPath, 7, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 7, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.balanceType(jsResponse, "Disburse_Balance");
        assertionResponse.value(jsResponse);
        assertionResponse.currency(jsResponse, testData.currency());
    }

    // @Test
    public void case3o7() {

        List<String> expected = intro.expectedDefinition(excelPath, 8, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 8, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);

    }

    // @Test
    public void case3o8() {

        List<String> expected = intro.expectedDefinition(excelPath, 9, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 9, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);

    }

    // @Test
    public void case3o9() {

        List<String> expected = intro.expectedDefinition(excelPath, 10, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 10, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);

    }

    // @Test
    public void case3o10() {

        List<String> expected = intro.expectedDefinition(excelPath, 11, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);

    }

}
