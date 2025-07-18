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

    @Test
    public void case1() {

        List<String> expected = intro.expectedDefinition(excelPath, 1);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 1);
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

    @Test
    public void case2() {

        List<String> expected = intro.expectedDefinition(excelPath, 2);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 2);
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

    @Test
    public void case3() {

        String requestCase = excelReader.requestCell(excelPath, 3);
        String headerRequestString = separateCell.header(requestCase);
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedDefinition(excelPath, 3);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 3);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        JSONObject jsonObjectBody = new JSONObject(bodyRequestString);

        if (jsonObjectBody.has("additionalInfo.transferService")) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo",
                    "additionalInfo.transferService", "additionalInfo.amount.value", "additionalInfo.amount.currency",
                    "dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (jsonObjectBody.has("sourceAccountNo")) {
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
        } else if (jsonObjectBody.has("originalPartnerReferenceNo")) {
            Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                    "X-EXTERNAL-ID", "CHANNEL-ID");
            assertionRequest.checkMissingMandatoryFields(headerRequestString, mandatoryFieldsHeader);

            Set<String> mandatoryFieldsBody = Set.of("originalPartnerReferenceNo", "serviceCode",
                    "additionalInfo.msgId", "additionalInfo.dspsign");
            assertionRequest.checkMissingMandatoryFields(bodyRequestString, mandatoryFieldsBody);
        } else if (jsonObjectBody.has("accountNo")) {
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

    @Test
    public void case4() {

        List<String> expected = intro.expectedDefinition(excelPath, 4);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 4);
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

    @Test
    public void case5() {

        String requestCase = excelReader.requestCell(excelPath, 5);
        String bodyRequestString = separateCell.body(requestCase);
        List<String> expected = intro.expectedDefinition(excelPath, 5);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 5);
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
            assertionRequest.assertXTimeStamp(jsHeader);
            assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
            assertionRequest.assertXExternalId(jsHeader);
            assertionRequest.assertChannelID(jsHeader, testData.channelId());

            assertionRequest.assertPartnerReferenceNo(jsResponse);
            assertionRequest.beneBankCode(jsBody);
            assertionRequest.beneAccNo(jsBody);
            assertionRequest.transferService(jsBody, testData.transferService());
            assertionRequest.value(jsBody);
            assertionRequest.currency(jsBody, testData.currency());
            assertionRequest.dspSign(jsBody, testData.jwt());
        } else if (exe) {
            assertionRequest.assertXTimeStamp(jsHeader);
            assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
            assertionRequest.assertXExternalId(jsHeader);
            assertionRequest.assertChannelID(jsHeader, testData.channelId());

            assertionRequest.assertPartnerReferenceNo(jsBody);
            assertionRequest.sourceAccountNo(jsBody);
            assertionRequest.beneBankCode(jsBody);
            assertionRequest.beneAccNo(jsBody);
            assertionRequest.beneAccName(jsBody);
            assertionRequest.trxDate(jsBody);
            assertionRequest.value(jsBody);
            assertionRequest.currency(jsBody, testData.currency());
            assertionRequest.msgId(jsBody);
            // assertionRequest.disbCategory(jsBody, expectedRM);
            assertionRequest.senderName(jsBody);
            assertionRequest.accType(jsBody);
            assertionRequest.accInstId(jsBody);
            assertionRequest.country(jsBody);
            assertionRequest.city(jsBody);
            assertionRequest.identificationNo(jsBody);
            assertionRequest.dspSign(jsBody, testData.jwt());

        } else if (checkStatus) {
            assertionRequest.assertXTimeStamp(jsHeader);
            assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
            assertionRequest.assertXExternalId(jsHeader);
            assertionRequest.assertChannelID(jsHeader, testData.channelId());

            assertionRequest.assertPartnerReferenceNo(jsResponse);
            assertionRequest.serviceCode(jsBody);
            assertionRequest.msgId(jsBody);
            assertionRequest.dspSign(jsBody, testData.jwt());

        } else if (getBalance) {
            assertionRequest.assertXTimeStamp(jsHeader);
            assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
            assertionRequest.assertXExternalId(jsHeader);
            assertionRequest.assertChannelID(jsHeader, testData.channelId());

            assertionRequest.accNo(jsBody, testData.partnerId());
            assertionRequest.dspSign(jsBody, testData.jwt());

        } else {
            System.out.print("Unique Field Tidak Ditemukan");
        }

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
    }

    @Test
    public void case7() {

        List<String> expected = intro.expectedDefinition(excelPath, 7);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 7);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsResponse);
        assertionRequest.beneBankCode(jsBody);
        assertionRequest.beneAccName(jsBody);
        assertionRequest.transferService(jsBody, testData.transferService());
        assertionRequest.value(jsBody);
        assertionRequest.currency(jsBody, testData.currency());
        assertionRequest.dspSign(jsBody, testData.jwt());

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccName(jsResponse);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertMsgId(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    @Test
    public void case9() {

        List<String> expected = intro.expectedDefinition(excelPath, 9);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 9);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsResponse);
        assertionRequest.beneBankCode(jsBody);
        assertionRequest.beneAccName(jsBody);
        assertionRequest.transferService(jsBody, testData.transferService());
        assertionRequest.value(jsBody);
        assertionRequest.currency(jsBody, testData.currency());
        assertionRequest.dspSign(jsBody, testData.jwt());

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccName(jsResponse);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertMsgId(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    @Test
    public void case11() {

        List<String> expected = intro.expectedDefinition(excelPath, 11);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsBody);
        assertionRequest.sourceAccountNo(jsBody);
        assertionRequest.beneBankCode(jsBody);
        assertionRequest.beneAccNo(jsBody);
        assertionRequest.beneAccName(jsBody);
        assertionRequest.trxDate(jsBody);
        assertionRequest.value(jsBody);
        assertionRequest.currency(jsBody, testData.currency());
        assertionRequest.msgId(jsBody);
        // assertionRequest.disbCategory(jsBody, expectedRM);
        assertionRequest.senderName(jsBody);
        assertionRequest.accType(jsBody);
        assertionRequest.accInstId(jsBody);
        assertionRequest.country(jsBody);
        assertionRequest.city(jsBody);
        assertionRequest.identificationNo(jsBody);
        assertionRequest.dspSign(jsBody, testData.jwt());

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");

    }

    @Test
    public void case12() {

        List<String> expected = intro.expectedDefinition(excelPath, 11);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsBody);
        assertionRequest.sourceAccountNo(jsBody);
        assertionRequest.beneBankCode(jsBody);
        assertionRequest.beneAccNo(jsBody);
        assertionRequest.beneAccName(jsBody);
        assertionRequest.trxDate(jsBody);
        assertionRequest.value(jsBody);
        assertionRequest.currency(jsBody, testData.currency());
        assertionRequest.msgId(jsBody);
        // assertionRequest.disbCategory(jsBody, expectedRM);
        assertionRequest.senderName(jsBody);
        assertionRequest.accType(jsBody);
        assertionRequest.accInstId(jsBody);
        assertionRequest.country(jsBody);
        assertionRequest.city(jsBody);
        assertionRequest.identificationNo(jsBody);
        assertionRequest.dspSign(jsBody, testData.jwt());

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    @Test
    public void case13() {

        List<String> expected = intro.expectedDefinition(excelPath, 11);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsBody);
        assertionRequest.sourceAccountNo(jsBody);
        assertionRequest.beneBankCode(jsBody);
        assertionRequest.beneAccNo(jsBody);
        assertionRequest.beneAccName(jsBody);
        assertionRequest.trxDate(jsBody);
        assertionRequest.value(jsBody);
        assertionRequest.currency(jsBody, testData.currency());
        assertionRequest.msgId(jsBody);
        // assertionRequest.disbCategory(jsBody, expectedRM);
        assertionRequest.senderName(jsBody);
        assertionRequest.accType(jsBody);
        assertionRequest.accInstId(jsBody);
        assertionRequest.country(jsBody);
        assertionRequest.city(jsBody);
        assertionRequest.identificationNo(jsBody);
        assertionRequest.dspSign(jsBody, testData.jwt());

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

    @Test
    public void case14() {

        List<String> expected = intro.expectedDefinition(excelPath, 11);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 11);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsResponse);
        assertionRequest.serviceCode(jsBody);
        assertionRequest.msgId(jsBody);
        assertionRequest.dspSign(jsBody, testData.jwt());

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.serviceCode(jsResponse);
        assertionResponse.sourceAccountNo(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");
    }

}
