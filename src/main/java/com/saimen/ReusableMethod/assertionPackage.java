package com.saimen.ReusableMethod;

import com.saimen.constant.expected;

import io.restassured.path.json.JsonPath;

public class assertionPackage {

    public static void inquiry(JsonPath jsHeader, JsonPath jsBody, expected testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.PARTNERID);
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.CHANNELID);

        assertionRequest.assertPartnerReferenceNo(jsBody);
        assertionRequest.beneBankCode(jsBody, testData);
        assertionRequest.beneAccNo(jsBody);
        assertionRequest.transferService(jsBody, testData.TRANSFERSERVICE);
        assertionRequest.value(jsBody);
        assertionRequest.currency(jsBody, testData.CURRENCY);
        assertionRequest.dspSign(jsBody, testData.JWT);
    }

    public static void exe(JsonPath jsHeader, JsonPath jsBody, expected testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.PARTNERID);
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.CHANNELID);

        assertionRequest.assertPartnerReferenceNo(jsBody);
        assertionRequest.sourceAccountNo(jsBody);
        assertionRequest.beneBankCode(jsBody, testData);
        assertionRequest.beneAccNo(jsBody);
        assertionRequest.beneAccName(jsBody);
        assertionRequest.trxDate(jsBody);
        assertionRequest.valueExe(jsBody);
        assertionRequest.currencyExe(jsBody, testData.CURRENCY);
        assertionRequest.msgId(jsBody);
        // assertionRequest.disbCategory(jsBody, expectedRM);
        assertionRequest.senderName(jsBody);
        assertionRequest.accType(jsBody);
        assertionRequest.accInstId(jsBody);
        assertionRequest.country(jsBody);
        assertionRequest.city(jsBody);
        assertionRequest.identificationNo(jsBody);
        assertionRequest.dspSign(jsBody, testData.JWT);
    }

    public static void checkStatus(JsonPath jsHeader, JsonPath jsBody, expected testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.PARTNERID);
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.CHANNELID);

        assertionRequest.assertOriPartnerReferenceNo(jsBody);
        assertionRequest.serviceCode(jsBody);
        assertionRequest.msgId(jsBody);
        assertionRequest.dspSign(jsBody, testData.JWT);
    }

    public static void getBalance(JsonPath jsHeader, JsonPath jsBody, expected testData) {
        assertionRequest.assertXTimeStamp(jsHeader);
        assertionRequest.assertXPartnerId(jsHeader, testData.PARTNERID);
        assertionRequest.assertXExternalId(jsHeader);
        assertionRequest.assertChannelID(jsHeader, testData.CHANNELID);

        assertionRequest.accNo(jsBody, testData.PARTNERID);
        assertionRequest.dspSign(jsBody, testData.JWT);
    }

}
