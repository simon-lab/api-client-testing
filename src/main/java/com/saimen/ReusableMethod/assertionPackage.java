package com.saimen.ReusableMethod;

import com.saimen.api.entity.Header;
import com.saimen.api.entity.Body;
import com.saimen.constant.expected;

import io.restassured.path.json.JsonPath;

public class assertionPackage {

    public static void inquiryHeader(Header head, expected testData) {
        assertionRequest.assertXTimeStamp(head);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID);
        assertionRequest.assertXExternalId(head);
        assertionRequest.assertChannelID(head, testData.CHANNELID);

    }

    public static void inquiryBody(Body body, expected testData) {
        assertionRequest.assertPartnerReferenceNo(body);
        assertionRequest.beneBankCode(body, testData);
        assertionRequest.beneAccNo(body);
        assertionRequest.transferService(body, testData.TRANSFERSERVICE);
        assertionRequest.value(body);
        assertionRequest.currency(body, testData.CURRENCY);
        assertionRequest.dspSign(body, testData.JWT);
    }

    public static void exe(Header head, Body body, expected testData) {
        assertionRequest.assertXTimeStamp(head);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID);
        assertionRequest.assertXExternalId(head);
        assertionRequest.assertChannelID(head, testData.CHANNELID);

        assertionRequest.assertPartnerReferenceNo(body);
        assertionRequest.sourceAccountNo(body);
        assertionRequest.beneBankCode(body, testData);
        assertionRequest.beneAccNo(body);
        assertionRequest.beneAccName(body);
        assertionRequest.trxDate(body);
        assertionRequest.valueExe(body);
        assertionRequest.currencyExe(body, testData.CURRENCY);
        assertionRequest.msgId(body);
        // assertionRequest.disbCategory(jsBody, expectedRM);
        assertionRequest.senderName(body);
        assertionRequest.accType(body);
        assertionRequest.accInstId(body);
        assertionRequest.country(body);
        assertionRequest.city(body);
        assertionRequest.identificationNo(body);
        assertionRequest.dspSign(body, testData.JWT);
    }

    public static void checkStatus(Header head, Body body, expected testData) {
        assertionRequest.assertXTimeStamp(head);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID);
        assertionRequest.assertXExternalId(head);
        assertionRequest.assertChannelID(head, testData.CHANNELID);

        assertionRequest.assertOriPartnerReferenceNo(body);
        assertionRequest.serviceCode(body);
        assertionRequest.msgId(body);
        assertionRequest.dspSign(body, testData.JWT);
    }

    public static void getBalance(Header head, Body body, expected testData) {
        assertionRequest.assertXTimeStamp(head);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID);
        assertionRequest.assertXExternalId(head);
        assertionRequest.assertChannelID(head, testData.CHANNELID);

        assertionRequest.accNo(body, testData.PARTNERID);
        assertionRequest.dspSign(body, testData.JWT);
    }

}
