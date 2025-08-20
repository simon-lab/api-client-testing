package com.saimen.api;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saimen.ReusableMethod.assertionPackage;
import com.saimen.ReusableMethod.assertionResponse;
import com.saimen.ReusableMethod.toRegex;
import com.saimen.api.entity.Body;
import com.saimen.constant.Expected;
import com.saimen.constant.expected;

import io.restassured.path.json.JsonPath;

@RestController
@RequestMapping("/interbank")
public class interbankController {

    @PostMapping("req/case3")
    public String case3RequestCheck(@RequestBody Body inq) {

        String expectedRC = "400xx02";
        String expectedRM = "Missing Mandatory Field {........}";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        String getPartnerRefNo = inq.getPartnerReferenceNo();

        return "Nih Reference No Nya:" + getPartnerRefNo;

    }

    @PostMapping("req/body/case7")
    public String case7RequestCheck(@RequestBody Body body) {

        Expected expected;

        String expectedRC = "2001600";
        String expectedRM = "success";

        String formatRC = toRegex.toRegexFormat(expectedRC);

        assertionPackage.inquiryBody(body, expected);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);
        assertionResponse.assertBeneAccName(jsResponse);
        assertionResponse.assertBeneAccNo(jsResponse);
        assertionResponse.assertReferenceNo(jsResponse, 12);
        assertionResponse.assertMsgId(jsResponse);
        assertionResponse.assertIsoRC(jsResponse, "00");
        assertionResponse.assertIsoMessage(jsResponse, "Success");

    }
}
