package tests.requestResponseValidation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.path.json.JsonPath;
import tests.requestResponseValidation.ReusableMethod.assertionPackage;
import tests.requestResponseValidation.ReusableMethod.assertionRequest;
import tests.requestResponseValidation.ReusableMethod.assertionResponse;
import tests.requestResponseValidation.ReusableMethod.attachmentRequestResponse;
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
    attachmentRequestResponse attachment = new attachmentRequestResponse();

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
    // public void case8o1() {

    // List<String> expected = intro.expectedErrorDefinition(excelPath, 1,
    // "interbank");
    // List<JsonPath> js = intro.caseDataDefinition(excelPath, 1, "interbank");
    // String expectedRC = expected.get(0);
    // String expectedRM = expected.get(1);
    // JsonPath jsHeader = js.get(0);
    // JsonPath jsBody = js.get(1);
    // JsonPath jsResponse = js.get(2);
    // String formatRC = toRegex.toRegexFormat(expectedRC);
    // System.out.println(formatRC);

    // assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
    // assertionResponse.assertResponseMessage(jsResponse, expectedRM);

    // }

    // @Test
    // public void case8o2() {

    // List<String> expected = intro.expectedErrorDefinition(excelPath, 2,
    // "interbank");
    // List<JsonPath> js = intro.caseDataDefinition(excelPath, 2, "interbank");
    // String expectedRC = expected.get(0);
    // String expectedRM = expected.get(1);
    // JsonPath jsHeader = js.get(0);
    // JsonPath jsBody = js.get(1);
    // JsonPath jsResponse = js.get(2);
    // String formatRC = toRegex.toRegexFormat(expectedRC);
    // System.out.println(formatRC);

    // assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
    // assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    // }

    @Test(priority = 3)
    @Epic("Interbank Transfer")
    @Feature("Case 8.3")
    public void case8o3() {
        String expectedString = excelReader.expectedCell(excelPath, 3, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 3, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedErrorDefinition(excelPath, 3, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 3, "interbank");
        String responseString = excelReader.responseCell(excelPath, 3, "interbank");
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

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo",
                    "additionalInfo.transferService", "additionalInfo.amount.value", "additionalInfo.amount.currency",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else if (exe) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "sourceAccountNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo", "beneAccountName", "transactionDate", "amount.value", "amount.currency",
                    "additionalInfo.msgId", "additionalInfo.disbCategory", "senderInfo.name", "senderInfo.accountType",
                    "senderInfo,accountInstId", "senderInfo.country", "senderInfo.city",
                    "senderInfo.identificationType",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else if (checkStatus) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");

            Set<String> mandatoryFieldsBody = Set.of("originalPartnerReferenceNo", "serviceCode",
                    "additionalInfo.msgId", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else if (getBalance) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");

            Set<String> mandatoryFieldsBody = Set.of("accountNo", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else {
            Assert.fail("Unique Field Tidak Ditemukan");
        }

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    // @Test
    // public void case8o4() {

    // List<String> expected = intro.expectedErrorDefinition(excelPath, 4,
    // "interbank");
    // List<JsonPath> js = intro.caseDataDefinition(excelPath, 4, "interbank");
    // String expectedRC = expected.get(0);
    // String expectedRM = expected.get(1);
    // JsonPath jsHeader = js.get(0);
    // JsonPath jsBody = js.get(1);
    // JsonPath jsResponse = js.get(2);
    // String formatRC = toRegex.toRegexFormat(expectedRC);
    // System.out.println(formatRC);

    // assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
    // assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    // }

    @Test(priority = 5)
    @Epic("Interbank Transfer")
    @Feature("Case 8.5")
    public void case8o5() {
        String expectedString = excelReader.expectedCell(excelPath, 5, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 5, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 5, "interbank");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 5, "interbank");
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
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 7)
    @Epic("Interbank Transfer")
    @Feature("Case 8.7")
    public void case8o7() {
        String expectedString = excelReader.expectedCell(excelPath, 7, "interbank");
        List<String> expected = intro.expectedResponseDefinition(excelPath, 7, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 7, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 7, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 7, "interbank");
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
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 9)
    @Epic("Interbank Transfer")
    @Feature("Case 8.9")
    public void case8o9() {
        String expectedString = excelReader.expectedCell(excelPath, 8, "interbank");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 8, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 8, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 8, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 8, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.inquiry(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 11)
    @Epic("Interbank Transfer")
    @Feature("Case 8.11")
    public void case8o11() {
        String expectedString = excelReader.expectedCell(excelPath, 10, "interbank");
        List<String> expected = intro.expectedResponseDefinition(excelPath, 10, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 10, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 10, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 10, "interbank");
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
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);

    }

    @Test(priority = 12)
    @Epic("Interbank Transfer")
    @Feature("Case 8.12")
    public void case8o12() {
        String expectedString = excelReader.expectedCell(excelPath, 11, "interbank");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 11, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 11, "interbank");
        String responseString = excelReader.responseCell(excelPath, 11, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
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
        assertionResponse.assertIsoRC(jsResponse, "51");
        assertionResponse.assertIsoMessage(jsResponse, "Insufficient Balance");
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 13)
    @Epic("Interbank Transfer")
    @Feature("Case 8.13")
    public void case8o13() {
        String expectedString = excelReader.expectedCell(excelPath, 12, "interbank");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 12, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 12, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 12, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 12, "interbank");
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
        assertionResponse.assertIsoRC(jsResponse, "94");
        assertionResponse.assertIsoMessage(jsResponse, "Duplicate Transaction");
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 14)
    @Epic("Interbank Transfer")
    @Feature("Case 8.14")
    public void case8o14() {
        String expectedString = excelReader.expectedCell(excelPath, 13, "interbank");
        List<String> expected = intro.expectedResponseDefinition(excelPath, 13, "interbank");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 13, "interbank");
        String requestCase = excelReader.requestCell(excelPath, 13, "interbank");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 13, "interbank");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.checkStatus(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertOriginalReferenceNo(jsResponse);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.serviceCode(jsResponse);
        assertionResponse.sourceAccountNo(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 23)
    @Epic("Balance Services")
    @Feature("Case 3.3")
    public void case3o3() {
        String expectedString = excelReader.expectedCell(excelPath, 3, "balance");
        String requestCase = excelReader.requestCell(excelPath, 3, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedErrorDefinition(excelPath, 3, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 3, "balance");
        String responseString = excelReader.responseCell(excelPath, 3, "balance");
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

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo",
                    "additionalInfo.transferService", "additionalInfo.amount.value", "additionalInfo.amount.currency",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else if (exe) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "sourceAccountNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo", "beneAccountName", "transactionDate", "amount.value", "amount.currency",
                    "additionalInfo.msgId", "additionalInfo.disbCategory", "senderInfo.name", "senderInfo.accountType",
                    "senderInfo,accountInstId", "senderInfo.country", "senderInfo.city",
                    "senderInfo.identificationType",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else if (checkStatus) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");

            Set<String> mandatoryFieldsBody = Set.of("originalPartnerReferenceNo", "serviceCode",
                    "additionalInfo.msgId", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else if (getBalance) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");

            Set<String> mandatoryFieldsBody = Set.of("accountNo", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader, bodyRequestString,
                    mandatoryFieldsBody);
        } else {
            Assert.fail("Unique Field Tidak Ditemukan");
        }

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 24)
    @Epic("Balance Services")
    @Feature("Case 3.4")
    public void case3o4() {
        String expectedString = excelReader.expectedCell(excelPath, 5, "balance");
        String requestCase = excelReader.requestCell(excelPath, 5, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 5, "balance");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 5, "balance");
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
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 25)
    @Epic("Balance Services")
    @Feature("Case 3.5")
    public void case3o5() {
        String expectedString = excelReader.expectedCell(excelPath, 6, "balance");
        List<String> expected = intro.expectedResponseDefinition(excelPath, 6, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 6, "balance");
        String requestCase = excelReader.requestCell(excelPath, 6, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 6, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.balanceTypeBalance(jsResponse, "DISBURSE_BALANCE");
        assertionResponse.value(jsResponse);
        assertionResponse.currency(jsResponse, testData.currency());
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 26)
    @Epic("Balance Services")
    @Feature("Case 3.6")
    public void case3o6() {
        String expectedString = excelReader.expectedCell(excelPath, 7, "balance");
        List<String> expected = intro.expectedResponseDefinition(excelPath, 7, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 7, "balance");
        String requestCase = excelReader.requestCell(excelPath, 7, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 7, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.balanceTypeBalance(jsResponse, "DISBURSE_BALANCE");
        assertionResponse.value(jsResponse);
        assertionResponse.currency(jsResponse, testData.currency());
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);
    }

    @Test(priority = 27)
    @Epic("Balance Services")
    @Feature("Case 3.7")
    public void case3o7() {
        String expectedString = excelReader.expectedCell(excelPath, 8, "balance");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 8, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 8, "balance");
        String requestCase = excelReader.requestCell(excelPath, 8, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 8, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);

    }

    @Test(priority = 28)
    @Epic("Balance Services")
    @Feature("Case 3.8")
    public void case3o8() {
        String expectedString = excelReader.expectedCell(excelPath, 9, "balance");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 9, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 9, "balance");
        String requestCase = excelReader.requestCell(excelPath, 9, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 9, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);

    }

    @Test(priority = 29)
    @Epic("Balance Services")
    @Feature("Case 3.9")
    public void case3o9() {
        String expectedString = excelReader.expectedCell(excelPath, 10, "balance");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 10, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 10, "balance");
        String requestCase = excelReader.requestCell(excelPath, 10, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 10, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);

    }

    @Test(priority = 30)
    @Epic("Balance Services")
    @Feature("Case 3.10")
    public void case3o10() {
        String expectedString = excelReader.expectedCell(excelPath, 11, "balance");
        List<String> expected = intro.expectedErrorDefinition(excelPath, 11, "balance");
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11, "balance");
        String requestCase = excelReader.requestCell(excelPath, 11, "balance");
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        String responseString = excelReader.responseCell(excelPath, 3, "balance");
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.getBalance(jsHeader, jsBody, testData);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        attachment.attach(expectedString, headerRequestString, bodyRequestString, responseString);

    }

}
