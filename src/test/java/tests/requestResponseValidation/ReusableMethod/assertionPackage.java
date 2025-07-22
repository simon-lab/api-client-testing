package tests.requestResponseValidation.ReusableMethod;

import io.restassured.path.json.JsonPath;
import tests.requestResponseValidation.model.clientTestData;

public class assertionPackage {

    public static void inquiry(JsonPath jsHeader, JsonPath jsBody, clientTestData testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsBody);
        assertionRequest.beneBankCode(jsBody, testData);
        assertionRequest.beneAccNo(jsBody);
        assertionRequest.transferService(jsBody, testData.transferService());
        assertionRequest.value(jsBody);
        assertionRequest.currency(jsBody, testData.currency());
        assertionRequest.dspSign(jsBody, testData.jwt());
    }

    public static void exe(JsonPath jsHeader, JsonPath jsBody, clientTestData testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertPartnerReferenceNo(jsBody);
        assertionRequest.sourceAccountNo(jsBody);
        assertionRequest.beneBankCode(jsBody, testData);
        assertionRequest.beneAccNo(jsBody);
        assertionRequest.beneAccName(jsBody);
        assertionRequest.trxDate(jsBody);
        assertionRequest.valueExe(jsBody);
        assertionRequest.currencyExe(jsBody, testData.currency());
        assertionRequest.msgId(jsBody);
        // assertionRequest.disbCategory(jsBody, expectedRM);
        assertionRequest.senderName(jsBody);
        assertionRequest.accType(jsBody);
        assertionRequest.accInstId(jsBody);
        assertionRequest.country(jsBody);
        assertionRequest.city(jsBody);
        assertionRequest.identificationNo(jsBody);
        assertionRequest.dspSign(jsBody, testData.jwt());
    }

    public static void checkStatus(JsonPath jsHeader, JsonPath jsBody, clientTestData testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.assertOriPartnerReferenceNo(jsBody);
        assertionRequest.serviceCode(jsBody);
        assertionRequest.msgId(jsBody);
        assertionRequest.dspSign(jsBody, testData.jwt());
    }

    public static void getBalance(JsonPath jsHeader, JsonPath jsBody, clientTestData testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.partnerId());
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.channelId());

        assertionRequest.accNo(jsBody, testData.partnerId());
        assertionRequest.dspSign(jsBody, testData.jwt());
    }

}
