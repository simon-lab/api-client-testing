package com.saimen.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saimen.AssertionMethod.assertionPackage;
import com.saimen.AssertionMethod.assertionRequest;
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

    @PostMapping("req/header/case1")
    public ResponseEntity<ValidationResult> case1HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.header(head, expected);
        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case1")
    public ResponseEntity<ValidationResult> case1BodyRequestCheck(@RequestBody Body body) {
        ValidationContext ctx = new ValidationContext();
        expected expected = new expected();
        Boolean inquiry = false;

        var add = body.getAdditionalInfo();

        if (add != null) {
            var inq = body.getAdditionalInfo().getTransferService();
            inquiry = inq != null;
        }

        var exe = body.getSourceAccountNo();
        Boolean execution = exe != null;
        var check = body.getOriginalPartnerReferenceNo();
        Boolean checkStatus = check != null;
        var balance = body.getAccountNo();
        Boolean getBalance = balance != null;

        ValidationResult result = null;

        if (inquiry) {

            result = assertionPackage.inquiryBody(body, expected);
        } else if (execution) {

            result = assertionPackage.exeBody(body, expected);
        } else if (checkStatus) {

            result = assertionPackage.checkStatusBody(body, expected);
        } else if (getBalance) {

            result = assertionPackage.getBalanceBody(body, expected);
        } else {
            ctx.addError("Unique Field Tidak Ditemukan");
        }

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case1")
    public ResponseEntity<ValidationResult> case1ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "401xx01";
        String expectedRM = "Unauthorized Signature";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case2")
    public ResponseEntity<ValidationResult> case2HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.header(head, expected);
        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case2")
    public ResponseEntity<ValidationResult> case2BodyRequestCheck(@RequestBody Body body) {
        ValidationContext ctx = new ValidationContext();
        expected expected = new expected();
        Boolean inquiry = false;

        var add = body.getAdditionalInfo();

        if (add != null) {
            var inq = body.getAdditionalInfo().getTransferService();
            inquiry = inq != null;
        }

        var exe = body.getSourceAccountNo();
        Boolean execution = exe != null;
        var check = body.getOriginalPartnerReferenceNo();
        Boolean checkStatus = check != null;
        var balance = body.getAccountNo();
        Boolean getBalance = balance != null;

        ValidationResult result = null;

        if (inquiry) {

            result = assertionPackage.inquiryBody(body, expected);
        } else if (execution) {

            result = assertionPackage.exeBody(body, expected);
        } else if (checkStatus) {

            result = assertionPackage.checkStatusBody(body, expected);
        } else if (getBalance) {

            result = assertionPackage.getBalanceBody(body, expected);
        } else {
            ctx.addError("Unique Field Tidak Ditemukan");
        }

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case2")
    public ResponseEntity<ValidationResult> case2ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "401xx00";
        String expectedRM = "Access Token Invalid";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case3")
    public ResponseEntity<ValidationResult> case3HeaderRequestCheck(@RequestBody Header head) {
        ValidationContext ctx = new ValidationContext();
        Set<String> mandatoryFieldsHeader = Set.of("Authorization", "X-TIMESTAMP", "X-SIGNATURE", "X-PARTNER-ID",
                "X-EXTERNAL-ID", "CHANNEL-ID");

        assertionRequest.checkMissingHeaderMandatoryFields(head, mandatoryFieldsHeader, ctx);

        ValidationResult result = ctx.successResult();
        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case3")
    public ResponseEntity<ValidationResult> case3BodyRequestCheck(@RequestBody Body body) {
        ValidationContext ctx = new ValidationContext();
        Boolean inquiry = false;

        var add = body.getAdditionalInfo();

        if (add != null) {
            var inq = body.getAdditionalInfo().getTransferService();
            inquiry = inq != null;
        }

        var exe = body.getSourceAccountNo();
        Boolean execution = exe != null;
        var check = body.getOriginalPartnerReferenceNo();
        Boolean checkStatus = check != null;
        var balance = body.getAccountNo();
        Boolean getBalance = balance != null;

        if (inquiry) {

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo",
                    "additionalInfo.transferService", "additionalInfo.amount.value", "additionalInfo.amount.currency",
                    "additionalInfo.dspsign");
            assertionRequest.checkMissingBodyMandatoryFields(body, mandatoryFieldsBody, ctx);
        } else if (execution) {

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "sourceAccountNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo", "beneficiaryAccountName", "transactionDate", "amount.value",
                    "amount.currency",
                    "additionalInfo.msgId", "additionalInfo.disbCategory", "additionalInfo.senderInfo.name",
                    "additionalInfo.senderInfo.accountType",
                    "additionalInfo.senderInfo.accountInstId", "additionalInfo.senderInfo.country",
                    "additionalInfo.senderInfo.city",
                    "additionalInfo.senderInfo.identificationType",
                    "additionalInfo.dspsign");
            assertionRequest.checkMissingBodyMandatoryFields(body, mandatoryFieldsBody, ctx);
        } else if (checkStatus) {

            Set<String> mandatoryFieldsBody = Set.of("originalPartnerReferenceNo", "serviceCode",
                    "additionalInfo.msgId", "additionalInfo.dspsign");
            assertionRequest.checkMissingBodyMandatoryFields(body, mandatoryFieldsBody, ctx);
        } else if (getBalance) {

            Set<String> mandatoryFieldsBody = Set.of("accountNo", "additionalInfo.dspsign");
            assertionRequest.checkMissingBodyMandatoryFields(body, mandatoryFieldsBody, ctx);
        } else {
            ctx.addError("Unique Field Tidak Ditemukan");
        }

        ValidationResult result = ctx.successResult();
        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case3")
    public ResponseEntity<ValidationResult> case3ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();
        String expectedRC = "400xx02";
        String expectedRM = "Invalid Mandatory Field";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case4")
    public ResponseEntity<ValidationResult> case4ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "400xx01";
        String expectedRM = "Invalid Field Format";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case5")
    public ResponseEntity<ValidationResult> case5HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.header(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case5")
    public ResponseEntity<ValidationResult> case5BodyRequestCheck(@RequestBody Body body) {
        ValidationContext ctx = new ValidationContext();
        expected expected = new expected();
        Boolean inquiry = false;

        var add = body.getAdditionalInfo();

        if (add != null) {
            var inq = body.getAdditionalInfo().getTransferService();
            inquiry = inq != null;
        }

        var exe = body.getSourceAccountNo();
        Boolean execution = exe != null;
        var check = body.getOriginalPartnerReferenceNo();
        Boolean checkStatus = check != null;
        var balance = body.getAccountNo();
        Boolean getBalance = balance != null;

        ValidationResult result = null;

        if (inquiry) {

            result = assertionPackage.inquiryBody(body, expected);
        } else if (execution) {

            result = assertionPackage.exeBody(body, expected);
        } else if (checkStatus) {

            result = assertionPackage.checkStatusBody(body, expected);
        } else if (getBalance) {

            result = assertionPackage.getBalanceBody(body, expected);
        } else {
            ctx.addError("Unique Field Tidak Ditemukan");
        }

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);
    }

    @PostMapping("resp/case5")
    public ResponseEntity<ValidationResult> case5ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "409xx00";
        String expectedRM = "Conflict";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case6")
    public ResponseEntity<ValidationResult> case6HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.header(head, expected);

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

        ValidationResult result = assertionPackage.header(head, expected);

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

        ValidationResult result = assertionPackage.header(head, expected);

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

        ValidationResult result = assertionPackage.header(head, expected);

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

        ValidationResult result = assertionPackage.header(head, expected);

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

        ValidationResult result = assertionPackage.header(head, expected);

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

        String expectedRC = "4011102";
        String expectedRM = "Invalid Customer Token";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

}
