package tests.requestResponseValidation.ReusableMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReader {

    public static List<String> expectedReader(String excelFilePath, String service) {
        List<String> expectedList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = null;
            int startRow = 0;
            int endRow = 0;

            if (service.equalsIgnoreCase("interbank")) {
                sheet = workbook.getSheetAt(0);

                startRow = 9;
                endRow = 21;
            } else if (service.equalsIgnoreCase("balance")) {
                sheet = workbook.getSheetAt(1);

                startRow = 9;
                endRow = 15;
            }

            for (int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
                Row dataRow = sheet.getRow(rowIndex);

                Cell dataCell = dataRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                String value = dataCell.toString();

                expectedList.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return expectedList;
    }

    public static List<String> requestReader(String excelFilePath, String service) {
        List<String> requestList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = null;
            int startRow = 0;
            int endRow = 0;

            if (service.equalsIgnoreCase("interbank")) {
                sheet = workbook.getSheetAt(0);

                startRow = 9;
                endRow = 21;
            } else if (service.equalsIgnoreCase("balance")) {
                sheet = workbook.getSheetAt(1);

                startRow = 9;
                endRow = 15;
            }

            for (int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
                Row dataRow = sheet.getRow(rowIndex);

                Cell dataCell = dataRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                String value = dataCell.toString();

                requestList.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestList;
    }

    public static List<String> responseReader(String excelFilePath, String service) {
        List<String> responseList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = null;
            int startRow = 0;
            int endRow = 0;

            if (service.equalsIgnoreCase("interbank")) {
                sheet = workbook.getSheetAt(0);

                startRow = 9;
                endRow = 21;
            } else if (service.equalsIgnoreCase("balance")) {
                sheet = workbook.getSheetAt(1);

                startRow = 9;
                endRow = 15;
            }

            for (int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
                Row dataRow = sheet.getRow(rowIndex);

                Cell dataCell = dataRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                String value = dataCell.toString();

                responseList.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseList;
    }

    public static String expectedCell(String excelPath, int noCase, String service) {
        String request = expectedReader(excelPath, service).get(noCase - 1);
        return request;
    }

    public static String requestCell(String excelPath, int noCase, String service) {
        String request = requestReader(excelPath, service).get(noCase - 1);
        return request;
    }

    public static String responseCell(String excelPath, int noCase, String service) {
        String request = responseReader(excelPath, service).get(noCase - 1);
        return request;
    }

    // public static List<String> separateHeaderBody(String cellContent) {
    // String[] parts = cellContent.split("}");
    // List<String> headerBody = null;

    // if (parts.length >= 2) {
    // String header = parts[0].trim() + "\n}";

    // String body = parts[1].trim() + "\n}";

    // headerBody.add(header);
    // headerBody.add(body);

    // // System.out.println("Header: " + header);
    // // System.out.println("Body: " + body);
    // } else {
    // System.out.println("Format tidak sesuai, tidak ada pemisahan yang jelas.");
    // }

    // return headerBody;
    // }

    public static void main(String[] args) {
        String excelPath = "src/test/java/tests/requestResponseValidation/model/TestcaseSNAPBI.xlsx";
        List<String> expectedData = expectedReader(excelPath, "interbank");
        List<String> requestData = requestReader(excelPath, "interbank");
        List<String> responseData = responseReader(excelPath, "interbank");
        System.out.println("INI EXPECTED RESULT");
        System.out.println(expectedData);
        System.out.println("INI REQUEST");
        System.out.println(requestData);
        System.out.println("INI RESPONSE");
        System.out.println(responseData);

        // System.out.println("INI EXPECTED CASE 1");
        // String expectedCase1 = expectedCell(excelPath, 1);
        // System.out.println(expectedCell(excelPath, 1));
        // System.out.println("INI EXPECTED CASE 2");
        // System.out.println(expectedCell(excelPath, 2));

        // String requestCase1 = requestCell(excelPath, 1);
        // System.out.println("INI REQUEST CASE 1");
        // System.out.println(requestCase1);
        // System.out.println("INI REQUEST CASE 2");
        // System.out.println(requestCell(excelPath, 2));

        // System.out.println("INI PISAHAN HEADER DAN BODY: ");
        // String headerRequest1 = separateHeaderBody.header(requestCase1);
        // System.out.println(headerRequest1);
        // String bodyRequest1 = separateHeaderBody.body(requestCase1);
        // System.out.println(bodyRequest1);

        // JsonPath jsRequestBody1 = parsingJson.rawToJson(bodyRequest1);
        // JsonPath jsRequestHeader1 = parsingJson.rawToJson(headerRequest1);
        // System.out.println("INI X-EXTERNAL-ID NYA: " +
        // jsRequestHeader1.getString("X-EXTERNAL-ID"));
        // System.out.println("INI partnerReferenceNo NYA: " +
        // jsRequestBody1.getString("partnerReferenceNo"));

        // String responseCase1 = responseCell(excelPath, 1);
        // System.out.println("INI RESPONSE CASE 1");
        // System.out.println(responseCase1);
        // System.out.println("INI RESPONSE CASE 2");
        // System.out.println(responseCell(excelPath, 2));

        // JsonPath js = parsingJson.rawToJson(responseCase1);
        // System.out.println("INI RESPONSE CODE NYA: " + js.getString("responseCode"));
    }

}
