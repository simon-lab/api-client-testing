package tests.requestResponseValidation;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import tests.requestResponseValidation.ReusableMethod.excelReader;
import tests.requestResponseValidation.ReusableMethod.parsingJson;
import tests.requestResponseValidation.ReusableMethod.separateCell;
import tests.requestResponseValidation.model.clientTestData;
import util.JsonUtil;

public class testValidation {

    private clientTestData testData;
    String excelPath;
    excelReader excelReader = new excelReader();
    separateCell separate = new separateCell();
    parsingJson parsingJson = new parsingJson();

    @BeforeTest
    @Parameters({ "testDataPath" })
    public void setData(String testDataPath) {
        Allure.step("Set Excel");
        this.testData = JsonUtil.getTestData(testDataPath, clientTestData.class);
        excelPath = testData.excelPath();
    }

    @BeforeMethod
    public void monitorMethod(Method method) {
        System.out.println("Akan menjalankan test: " + method.getName());
    }

    @Test
    public void case1() {

        List<String> expectedData = excelReader.expectedReader(excelPath);
        List<String> requestData = excelReader.requestReader(excelPath);
        List<String> responseData = excelReader.responseReader(excelPath);
        System.out.println("INI EXPECTED RESULT");
        System.out.println(expectedData);
        System.out.println("INI REQUEST");
        System.out.println(requestData);
        System.out.println("INI RESPONSE");
        System.out.println(responseData);

        // String expected = excelReader.expectedCell(excelPath, 1);
        // String requestCase = excelReader.requestCell(excelPath, 1);
        // String headerRequest = separateCell.header(requestCase);
        // String bodyRequest = separateCell.body(requestCase);
        // JsonPath jsRequestBody = parsingJson.rawToJson(bodyRequest);
        // JsonPath jsRequestHeader = parsingJson.rawToJson(headerRequest);
        // String responseCase = excelReader.responseCell(excelPath, 1);
        // JsonPath jsResponse = parsingJson.rawToJson(responseCase);

        // System.out.println("INI PISAHAN HEADER DAN BODY: ");
        // System.out.println(headerRequest);
        // System.out.println(bodyRequest);

        // System.out.println("INI RESPONSE CASE 1");
        // System.out.println(responseCase);

        // System.out.println("INI ASSERTION CASE 1 ");
    }
}
