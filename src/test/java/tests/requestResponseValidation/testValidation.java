package tests.requestResponseValidation;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import tests.requestResponseValidation.ReusableMethod.assertionRequest;
import tests.requestResponseValidation.ReusableMethod.assertionResponse;
import tests.requestResponseValidation.ReusableMethod.excelReader;
import tests.requestResponseValidation.ReusableMethod.methodIntro;
import tests.requestResponseValidation.ReusableMethod.parsingJson;
import tests.requestResponseValidation.ReusableMethod.separateCell;
import tests.requestResponseValidation.ReusableMethod.toRegex;
import tests.requestResponseValidation.model.clientTestData;
import util.JsonUtil;

public class testValidation {

    private clientTestData testData;
    String excelPath;
    excelReader excelReader = new excelReader();
    separateCell separate = new separateCell();
    parsingJson parsingJson = new parsingJson();
    methodIntro intro = new methodIntro();
    String separator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    @BeforeTest
    @Parameters({ "testDataPath" })
    public void setData(String testDataPath) {
        Allure.step("Set Excel");
        this.testData = JsonUtil.getTestData(testDataPath, clientTestData.class);
        excelPath = testData.excelPath();
        // List<String> expectedData = excelReader.expectedReader(excelPath);
        // List<String> requestData = excelReader.requestReader(excelPath);
        // List<String> responseData = excelReader.responseReader(excelPath);
        // System.out.println("INI EXPECTED RESULT");
        // System.out.println(expectedData);
        // System.out.println("INI REQUEST");
        // System.out.println(requestData);
        // System.out.println("INI RESPONSE");
        // System.out.println(responseData);
    }

    @BeforeMethod
    public void monitorMethod(Method method) {
        System.out.println(separator);
        System.out.println("Akan menjalankan test: " + method.getName());
    }

    @Test
    public void case1() {

        List<String> expected = intro.expectedDefinition(excelPath, 1);
        List<JsonPath> js = intro.caseDataDefinition(excelPath, 1);
        String expectedRC = expected.get(0);
        String expectedRM = expected.get(1);
        JsonPath jsHeader = js.get(0);
        JsonPath jsBody = js.get(1);
        JsonPath jsResponse = js.get(2);
        String formatRC = toRegex.toRegexFormat(expectedRC);
        System.out.println(formatRC);

        assertionResponse.assertResponseCode(jsResponse, 7, formatRC);
        assertionResponse.assertResponseMessage(jsResponse, expectedRM);

    }

    // @Test
    // public void case2() {

    // intro.caseDataDefinition(excelPath, 2);
    // }

}
