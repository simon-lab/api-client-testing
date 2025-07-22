package tests.requestResponseValidation.ReusableMethod;

import java.util.ArrayList;
import java.util.List;

import io.restassured.path.json.JsonPath;

public class methodIntro {

    public static List<JsonPath> caseDataDefinition(String excelPath, int noCase, String service) {

        String requestCase = excelReader.requestCell(excelPath, noCase, service);
        String headerRequest = separateCell.header(requestCase);
        String bodyRequest = separateCell.body(requestCase);
        JsonPath jsRequestBody = parsingJson.rawToJson(bodyRequest);
        JsonPath jsRequestHeader = parsingJson.rawToJson(headerRequest);
        String responseCase = excelReader.responseCell(excelPath, noCase, service);
        JsonPath jsResponse = parsingJson.rawToJson(responseCase);
        List<JsonPath> jsGathering = new ArrayList<>();
        jsGathering.add(jsRequestHeader);
        jsGathering.add(jsRequestBody);
        jsGathering.add(jsResponse);

        System.out.println("INI HEADER: ");
        System.out.println(headerRequest);
        System.out.println("INI BODY: ");
        System.out.println(bodyRequest);

        System.out.println("INI RESPONSE CASE " + noCase);
        System.out.println(responseCase);

        System.out.println("INI ASSERTION CASE " + noCase);
        return jsGathering;

    }

    public static List<String> expectedErrorDefinition(String excelPath, int noCase, String service) {
        String expected = excelReader.expectedCell(excelPath, noCase, service);
        System.out.println("INI EXPECTED NYA Case " + noCase + ": ");
        System.out.println(expected);
        String rc = separateCell.extractExpectedEC(expected);
        String rm = separateCell.extractExpectedEM(expected).replace("\"", "");
        System.out.println("INI EXPECTED RESPONSE CODE" + noCase + ": ");
        System.out.println(rc);
        System.out.println("INI EXPECTED RESPONSE MESSAGE" + noCase + ": ");
        System.out.println(rm);
        List<String> expectedGathering = new ArrayList<>();
        expectedGathering.add(rc);
        expectedGathering.add(rm);
        return expectedGathering;
    }

    public static List<String> expectedResponseDefinition(String excelPath, int noCase, String service) {
        String expected = excelReader.expectedCell(excelPath, noCase, service);
        System.out.println("INI EXPECTED NYA Case " + noCase + ": ");
        System.out.println(expected);
        String rc = separateCell.extractExpectedRC(expected);
        String rm = separateCell.extractExpectedRM(expected).replace("\"", "");
        System.out.println("INI EXPECTED RESPONSE CODE" + noCase + ": ");
        System.out.println(rc);
        System.out.println("INI EXPECTED RESPONSE MESSAGE" + noCase + ": ");
        System.out.println(rm);
        List<String> expectedGathering = new ArrayList<>();
        expectedGathering.add(rc);
        expectedGathering.add(rm);
        return expectedGathering;
    }

}
