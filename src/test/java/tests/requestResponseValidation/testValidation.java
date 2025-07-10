package tests.requestResponseValidation;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import tests.requestResponseValidation.ReusableMethod.excelReader;
import tests.requestResponseValidation.ReusableMethod.parsingJson;
import tests.requestResponseValidation.ReusableMethod.separateHeaderBody;
import tests.requestResponseValidation.model.clientTestData;
import util.JsonUtil;

public class testValidation {

    private clientTestData testData;
    String excelPath;
    excelReader excelReader = new excelReader();
    separateHeaderBody separate = new separateHeaderBody();
    parsingJson parsingJson = new parsingJson();

    @BeforeTest
    @Parameters({ "testDataPath" })
    public void setData(String testDataPath) {
        Allure.step("Set Excel");
        this.testData = JsonUtil.getTestData(testDataPath, clientTestData.class);
        excelPath = testData.excelPath();
    }

    @Test
    public void case1() {
        String requestCase1 = excelReader.requestCell(excelPath, 1);
        System.out.println("INI REQUEST CASE 1");
        System.out.println(requestCase1);

        System.out.println("INI PISAHAN HEADER DAN BODY: ");
        String headerRequest1 = separateHeaderBody.header(requestCase1);
        System.out.println(headerRequest1);
        String bodyRequest1 = separateHeaderBody.body(requestCase1);
        System.out.println(bodyRequest1);

        JsonPath jsRequestBody1 = parsingJson.rawToJson(bodyRequest1);
        JsonPath jsRequestHeader1 = parsingJson.rawToJson(headerRequest1);
        System.out.println("INI X-EXTERNAL-ID NYA: " + jsRequestHeader1.getString("X-EXTERNAL-ID"));
        System.out.println("INI partnerReferenceNo NYA: " + jsRequestBody1.getString("partnerReferenceNo"));

        String responseCase1 = excelReader.responseCell(excelPath, 1);
        System.out.println("INI RESPONSE CASE 1");
        System.out.println(responseCase1);

        JsonPath js = parsingJson.rawToJson(responseCase1);
        System.out.println("INI RESPONSE CODE NYA: " + js.getString("responseCode"));
    }
}
