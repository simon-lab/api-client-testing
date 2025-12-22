package com.saimen.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.saimen.api.entity.Url;
import com.saimen.constant.expected;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/interbank")
public class interbankController {

    @PostMapping("req/url/case1")
    public ResponseEntity<ValidationResult> case1UrlRequestCheck(@RequestBody Url url) {

        String expectedService = "unknown";

        if (url.getDetectedService().equalsIgnoreCase("inquiry")) {
            expectedService = "inquiry";
        } else if (url.getDetectedService().equalsIgnoreCase("execution")) {
            expectedService = "execution";
        } else if (url.getDetectedService().equalsIgnoreCase("status")) {
            expectedService = "status";
        } else if (url.getDetectedService().equalsIgnoreCase("balance")) {
            expectedService = "balance";
        } else {
            expectedService = "unknown";
        }

        ValidationResult result = assertionPackage.url(url, expectedService);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

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
        String detectedService = "Unknown";

        if (inquiry) {
            detectedService = "inquiry";
            result = assertionPackage.inquiryBody(body, expected);
        } else if (execution) {
            detectedService = "execution";
            result = assertionPackage.exeBody(body, expected);
        } else if (checkStatus) {
            detectedService = "status";
            result = assertionPackage.checkStatusBody(body, expected);
        } else if (getBalance) {
            detectedService = "balance";
            result = assertionPackage.getBalanceBody(body, expected);
        } else {
            detectedService = "unknown";
            ctx.addError("Unique Field Tidak Ditemukan");
            result = ctx.toResult();
        }

        if (result != null) {
            result.setDetectedService(detectedService);
        }

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case1")
    public ResponseEntity<ValidationResult> case1ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "401xx01";
        String expectedRM = "Access Token Invalid";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);

        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/url/case2")
    public ResponseEntity<ValidationResult> case2UrlRequestCheck(@RequestBody Url url) {

        String expectedService = "unknown";

        if (url.getDetectedService().equalsIgnoreCase("inquiry")) {
            expectedService = "inquiry";
        } else if (url.getDetectedService().equalsIgnoreCase("execution")) {
            expectedService = "execution";
        } else if (url.getDetectedService().equalsIgnoreCase("status")) {
            expectedService = "status";
        } else if (url.getDetectedService().equalsIgnoreCase("balance")) {
            expectedService = "balance";
        } else {
            expectedService = "unknown";
        }

        ValidationResult result = assertionPackage.url(url, expectedService);

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
        String detectedService = "Unknown";

        if (inquiry) {
            detectedService = "inquiry";
            result = assertionPackage.inquiryBody(body, expected);
        } else if (execution) {
            detectedService = "execution";
            result = assertionPackage.exeBody(body, expected);
        } else if (checkStatus) {
            detectedService = "status";
            result = assertionPackage.checkStatusBody(body, expected);
        } else if (getBalance) {
            detectedService = "balance";
            result = assertionPackage.getBalanceBody(body, expected);
        } else {
            detectedService = "unknown";
            ctx.addError("Unique Field Tidak Ditemukan");
            result = ctx.toResult();
        }

        if (result != null) {
            result.setDetectedService(detectedService);
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

    @PostMapping("req/url/case3")
    public ResponseEntity<ValidationResult> case3UrlRequestCheck(@RequestBody Url url) {

        String expectedService = "unknown";

        if (url.getDetectedService().equalsIgnoreCase("inquiry")) {
            expectedService = "inquiry";
        } else if (url.getDetectedService().equalsIgnoreCase("execution")) {
            expectedService = "execution";
        } else if (url.getDetectedService().equalsIgnoreCase("status")) {
            expectedService = "status";
        } else if (url.getDetectedService().equalsIgnoreCase("balance")) {
            expectedService = "balance";
        } else {
            expectedService = "unknown";
        }

        ValidationResult result = assertionPackage.url(url, expectedService);

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

        ValidationResult result = null;

        if (inquiry) {

            Set<String> mandatoryFieldsBody = Set.of("partnerReferenceNo", "beneficiaryBankCode",
                    "beneficiaryAccountNo",
                    "additionalInfo.transferService", "additionalInfo.amount.value", "additionalInfo.amount.currency",
                    "additionalInfo.dspsign");
            assertionRequest.checkMissingBodyMandatoryFields(body, mandatoryFieldsBody, ctx);
            result = ctx.successResult();
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
            result = ctx.successResult();
        } else if (checkStatus) {

            Set<String> mandatoryFieldsBody = Set.of("originalPartnerReferenceNo", "serviceCode",
                    "additionalInfo.msgId", "additionalInfo.dspsign");
            assertionRequest.checkMissingBodyMandatoryFields(body, mandatoryFieldsBody, ctx);
            result = ctx.successResult();
        } else if (getBalance) {

            Set<String> mandatoryFieldsBody = Set.of("accountNo", "additionalInfo.dspsign");
            assertionRequest.checkMissingBodyMandatoryFields(body, mandatoryFieldsBody, ctx);
            result = ctx.successResult();
        } else {
            ctx.addError("Unique Field Tidak Ditemukan");
            result = ctx.toResult();
        }

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

    @PostMapping("req/url/case4")
    public ResponseEntity<ValidationResult> case4UrlRequestCheck(@RequestBody Url url) {

        String expectedService = "unknown";

        if (url.getDetectedService().equalsIgnoreCase("inquiry")) {
            expectedService = "inquiry";
        } else if (url.getDetectedService().equalsIgnoreCase("execution")) {
            expectedService = "execution";
        } else if (url.getDetectedService().equalsIgnoreCase("status")) {
            expectedService = "status";
        } else if (url.getDetectedService().equalsIgnoreCase("balance")) {
            expectedService = "balance";
        } else {
            expectedService = "unknown";
        }

        ValidationResult result = assertionPackage.url(url, expectedService);

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

    @PostMapping("req/url/case5")
    public ResponseEntity<ValidationResult> case5UrlRequestCheck(@RequestBody Url url) {

        String expectedService = "unknown";

        if (url.getDetectedService().equalsIgnoreCase("inquiry")) {
            expectedService = "inquiry";
        } else if (url.getDetectedService().equalsIgnoreCase("execution")) {
            expectedService = "execution";
        } else if (url.getDetectedService().equalsIgnoreCase("status")) {
            expectedService = "status";
        } else if (url.getDetectedService().equalsIgnoreCase("balance")) {
            expectedService = "balance";
        } else {
            expectedService = "unknown";
        }

        ValidationResult result = assertionPackage.url(url, expectedService);

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
        String detectedService = "Unknown";

        if (inquiry) {
            detectedService = "inquiry";
            result = assertionPackage.inquiryBody(body, expected);
        } else if (execution) {
            detectedService = "execution";
            result = assertionPackage.exeBody(body, expected);
        } else if (checkStatus) {
            detectedService = "status";
            result = assertionPackage.checkStatusBody(body, expected);
        } else if (getBalance) {
            detectedService = "balance";
            result = assertionPackage.getBalanceBody(body, expected);
        } else {
            detectedService = "unknown";
            ctx.addError("Unique Field Tidak Ditemukan");
            result = ctx.toResult();
        }

        if (result != null) {
            result.setDetectedService(detectedService);
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

    @PostMapping("req/url/case6")
    public ResponseEntity<ValidationResult> case6UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "inquiry");

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

        ValidationResult result = assertionPackage.inquiryBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case6")
    public ResponseEntity<ValidationResult> case6ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001600";
        String expectedRM = "Successful";

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

    @PostMapping("req/url/case7")
    public ResponseEntity<ValidationResult> case7UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "inquiry");

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

        ValidationResult result = assertionPackage.inquiryBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case7")
    public ResponseEntity<ValidationResult> case7ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001600";
        String expectedRM = "Successful";

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

    @PostMapping("req/url/case8")
    public ResponseEntity<ValidationResult> case8UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "inquiry");

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

        ValidationResult result = assertionPackage.inquiryBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case8")
    public ResponseEntity<ValidationResult> case8ResponseCheck(@RequestBody Response resp) {
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

    @PostMapping("req/url/case9")
    public ResponseEntity<ValidationResult> case9UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "execution");

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

        ValidationResult result = assertionPackage.exeBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case9")
    public ResponseEntity<ValidationResult> case9ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001800";
        String expectedRM = "Successful";

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

    @PostMapping("req/url/case10")
    public ResponseEntity<ValidationResult> case10UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "execution");

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

        ValidationResult result = assertionPackage.exeBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case10")
    public ResponseEntity<ValidationResult> case10ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2001800";
        String expectedRM = "Successful";

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

    @PostMapping("req/url/case11")
    public ResponseEntity<ValidationResult> case11UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "execution");

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

        ValidationResult result = assertionPackage.exeBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case11")
    public ResponseEntity<ValidationResult> case11ResponseCheck(@RequestBody Response resp) {
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

    @PostMapping("req/url/case12")
    public ResponseEntity<ValidationResult> case12UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "execution");

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case12")
    public ResponseEntity<ValidationResult> case12HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.header(head, expected);

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
    public ResponseEntity<ValidationResult> case12ResponseCheck(@RequestBody Response resp) {
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

    @PostMapping("req/url/case13")
    public ResponseEntity<ValidationResult> case13UrlRequestCheck(@RequestBody Url url) {

        ValidationResult result = assertionPackage.url(url, "status");

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/header/case13")
    public ResponseEntity<ValidationResult> case13HeaderRequestCheck(@RequestBody Header head) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.header(head, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("req/body/case13")
    public ResponseEntity<ValidationResult> case13BodyRequestCheck(@RequestBody Body body) {
        expected expected = new expected();

        ValidationResult result = assertionPackage.checkStatusBody(body, expected);

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }

    @PostMapping("resp/case13")
    public ResponseEntity<ValidationResult> case13ResponseCheck(@RequestBody Response resp) {
        ValidationContext ctxResp = new ValidationContext();

        String expectedRC = "2003600";
        String expectedRM = "Successful";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionResponse.assertResponseCode(resp, 7, formatRC, ctxResp);
        assertionResponse.assertResponseMessage(resp, expectedRM, ctxResp);
        assertionResponse.assertOriginalReferenceNo(resp, 12, ctxResp);
        assertionResponse.assertReferenceNo(resp, 12, ctxResp);
        assertionResponse.assertBeneAccNo(resp, ctxResp);
        assertionResponse.serviceCode(resp, "18", ctxResp);
        assertionResponse.sourceAccountNo(resp, 19, ctxResp);
        assertionResponse.assertIsoRC(resp, "00", ctxResp);
        assertionResponse.assertIsoMessage(resp, "success", ctxResp);
        ValidationResult result = ctxResp.toResult();

        return "OK".equals(result.getStatus())
                ? ResponseEntity.ok(result)
                : ResponseEntity.unprocessableEntity().body(result);

    }
}
