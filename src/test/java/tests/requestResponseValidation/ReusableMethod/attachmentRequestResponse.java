package tests.requestResponseValidation.ReusableMethod;

import io.qameta.allure.Allure;

public class attachmentRequestResponse {

    public static void attach(String expected, String requestHeader, String requestBody, String response) {
        Allure.addAttachment("Expected", expected);
        Allure.addAttachment("Header", requestHeader);
        Allure.addAttachment("Body", requestBody);
        Allure.addAttachment("Response", response);
    }

}
