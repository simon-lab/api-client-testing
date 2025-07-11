package tests.requestResponseValidation.ReusableMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

public class assertionRequest {

    public static void assertXTimeStamp(String timestamp) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timestamp);
        Assert.assertTrue(matcher.matches());
    }

    public static void assertXClientKey(String clientKey) {

    }

    public static void assertXSignature(String Signature) {

    }

}
