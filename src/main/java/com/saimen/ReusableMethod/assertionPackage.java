package com.saimen.ReusableMethod;

import com.saimen.api.entity.Header;
import com.saimen.api.dto.ValidationContext;
import com.saimen.api.dto.ValidationResult;
import com.saimen.api.entity.Body;
import com.saimen.constant.expected;

public class assertionPackage {

    public static void inquiryHeader(Header head, expected testData) {
        ValidationContext ctx = new ValidationContext();
        assertionRequest.assertXTimeStamp(head, ctx);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID, ctx);
        assertionRequest.assertXExternalId(head, ctx);
        assertionRequest.assertChannelID(head, testData.CHANNELID, ctx);

    }

    public static ValidationResult inquiryBody(Body body, expected testData) {
        ValidationContext ctx = new ValidationContext();

        assertionRequest.assertPartnerReferenceNo(body, ctx);
        assertionRequest.beneBankCode(body, ctx);
        assertionRequest.beneAccNo(body, ctx);
        assertionRequest.transferService(body, testData.TRANSFERSERVICE, ctx);
        assertionRequest.value(body, ctx);
        assertionRequest.currency(body, testData.CURRENCY, ctx);
        assertionRequest.dspSign(body, testData.JWT, ctx);

        return ctx.toResult();
    }

    public static void exeHeader(Header head, Body body, expected testData) {
        ValidationContext ctx = new ValidationContext();
        assertionRequest.assertXTimeStamp(head, ctx);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID, ctx);
        assertionRequest.assertXExternalId(head, ctx);
        assertionRequest.assertChannelID(head, testData.CHANNELID, ctx);
    }

    public static void exeBody(Header head, Body body, expected testData) {
        ValidationContext ctx = new ValidationContext();

        assertionRequest.assertPartnerReferenceNo(body, ctx);
        assertionRequest.sourceAccountNo(body, ctx);
        assertionRequest.beneBankCode(body, ctx);
        assertionRequest.beneAccNo(body, ctx);
        assertionRequest.beneAccName(body, ctx);
        assertionRequest.trxDate(body, ctx);
        assertionRequest.valueExe(body, ctx);
        assertionRequest.currencyExe(body, testData.CURRENCY, ctx);
        assertionRequest.msgId(body, ctx);
        // assertionRequest.disbCategory(jsBody, expectedRM);
        assertionRequest.senderName(body, ctx);
        assertionRequest.accType(body, ctx);
        assertionRequest.accInstId(body, ctx);
        assertionRequest.country(body, ctx);
        assertionRequest.city(body, ctx);
        assertionRequest.identificationNo(body, ctx);
        assertionRequest.dspSign(body, testData.JWT, ctx);
    }

    public static void checkStatusHeader(Header head, Body body, expected testData) {
        ValidationContext ctx = new ValidationContext();
        assertionRequest.assertXTimeStamp(head, ctx);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID, ctx);
        assertionRequest.assertXExternalId(head, ctx);
        assertionRequest.assertChannelID(head, testData.CHANNELID, ctx);
    }

    public static void checkStatusBody(Header head, Body body, expected testData) {
        ValidationContext ctx = new ValidationContext();

        assertionRequest.assertOriPartnerReferenceNo(body, ctx);
        assertionRequest.serviceCode(body, ctx);
        assertionRequest.msgId(body, ctx);
        assertionRequest.dspSign(body, testData.JWT, ctx);
    }

    public static void getBalanceHeader(Header head, Body body, expected testData) {
        ValidationContext ctx = new ValidationContext();
        assertionRequest.assertXTimeStamp(head, ctx);
        assertionRequest.assertXPartnerId(head, testData.PARTNERID, ctx);
        assertionRequest.assertXExternalId(head, ctx);
        assertionRequest.assertChannelID(head, testData.CHANNELID, ctx);
    }

    public static void getBalanceBody(Header head, Body body, expected testData) {
        ValidationContext ctx = new ValidationContext();

        assertionRequest.accNo(body, testData.PARTNERID, ctx);
        assertionRequest.dspSign(body, testData.JWT, ctx);
    }

}
