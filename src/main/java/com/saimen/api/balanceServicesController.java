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
@RequestMapping("/balance")
public class balanceServicesController {

    @PostMapping("req/header/case6")
    public ResponseEntity<ValidationResult> case6HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case6")
    public ResponseEntity<ValidationResult> case6BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case6")
    public ResponseEntity<ValidationResult> case6ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001100";
        String expectedRM = "success";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);
        assertionResponse.balanceType(resp, "Disburse_Balance", ctxResp);
        assertionResponse.value(resp, ctxResp);
        assertionResponse.currency(resp, "IDR", ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case7")
    public ResponseEntity<ValidationResult> case7HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case7")
    public ResponseEntity<ValidationResult> case7BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case7")
    public ResponseEntity<ValidationResult> case7ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001100";
        String expectedRM = "success";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);
        assertionResponse.balanceType(resp, "Disburse_Balance", ctxResp);
        assertionResponse.value(resp, ctxResp);
        assertionResponse.currency(resp, "IDR", ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case8")
    public ResponseEntity<ValidationResult> case8HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case8")
    public ResponseEntity<ValidationResult> case8BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case8")
    public ResponseEntity<ValidationResult> case8ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "4031118";
        String expectedRM = "Account Inactive";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case9")
    public ResponseEntity<ValidationResult> case9HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case9")
    public ResponseEntity<ValidationResult> case9BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case9")
    public ResponseEntity<ValidationResult> case9ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "4011100";
        String expectedRM = "Invalid Access Token Scope";

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

        ValidationResult result = assertionPackage.getBalanceHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case10")
    public ResponseEntity<ValidationResult> case10BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case10")
    public ResponseEntity<ValidationResult> case10ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "4031105";
        String expectedRM = "Account Frozen/Abnormal";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case11")
    public ResponseEntity<ValidationResult> case11HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceHeader(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case11")
    public ResponseEntity<ValidationResult> case11BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.getBalanceBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case11")
    public ResponseEntity<ValidationResult> case11ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "4011101";
        String expectedRM = "Access Token Invalid";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

}
