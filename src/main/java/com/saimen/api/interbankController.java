package com.saimen.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saimen.AssertionMethod.assertionPackage;
import com.saimen.AssertionMethod.assertionResponse;
import com.saimen.ReusableMethod.toRegex;
import com.saimen.api.dto.ValidationContext;
import com.saimen.api.dto.ValidationResult;
import com.saimen.api.entity.Body;
import com.saimen.api.entity.Header;
import com.saimen.api.entity.Response;
import com.saimen.constant.expected;

@RestController
@RequestMapping("/interbank")
public class interbankController {

    @PostMapping("req/case3")
    public String case3RequestCheck(@RequestBody Body inq) {

        String getPartnerRefNo = inq.getPartnerReferenceNo();

        return "Nih Reference No Nya:" + getPartnerRefNo;

    }

    @PostMapping("req/header/case7")
    public ResponseEntity<ValidationResult> case7HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.inquiryHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case7")
    public ResponseEntity<ValidationResult> case7BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.inquiryBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case7")
    public ResponseEntity<ValidationResult> case7ResponseRequestCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001600";
        String expectedRM = "success";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);
        assertionResponse.assertBeneAccName(resp, ctxResp);
        assertionResponse.assertBeneAccNo(resp, ctxResp);
        assertionResponse.assertReferenceNo(resp, 12, ctxResp);
        assertionResponse.assertMsgId(resp, 42, ctxResp);
        assertionResponse.assertIsoRC(resp, "00", ctxResp);
        assertionResponse.assertIsoMessage(resp, "Success", ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case8")
    public ResponseEntity<ValidationResult> case8HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.inquiryHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case8")
    public ResponseEntity<ValidationResult> case8BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.inquiryBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case8")
    public ResponseEntity<ValidationResult> case8ResponseRequestCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "4031618";
        String expectedRM = "Inactive Account";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case10")
    public ResponseEntity<ValidationResult> case10HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.exeHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case10")
    public ResponseEntity<ValidationResult> case10BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.exeBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case10")
    public ResponseEntity<ValidationResult> case10ResponseRequestCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001800";
        String expectedRM = "success";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);
        assertionResponse.assertReferenceNo(resp, 12, ctxResp);
        assertionResponse.assertBeneAccNo(resp, ctxResp);
        assertionResponse.assertIsoRC(resp, "00", ctxResp);
        assertionResponse.assertIsoMessage(resp, "Success", ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case11")
    public ResponseEntity<ValidationResult> case11HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.exeHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case11")
    public ResponseEntity<ValidationResult> case11BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.exeBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case11")
    public ResponseEntity<ValidationResult> case11ResponseRequestCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "4031814";
        String expectedRM = "Insufficient Fund";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case12")
    public ResponseEntity<ValidationResult> case12HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.exeHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case12")
    public ResponseEntity<ValidationResult> case12BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.exeBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case12")
    public ResponseEntity<ValidationResult> case12ResponseRequestCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "4031814";
        String expectedRM = "Insufficient Fund";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }
}
