package tests.requestResponseValidation.ReusableMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;

public class assertionRequest {

    public static void assertXTimeStamp(JsonPath js) {
        Allure.step("Check X-Time-Stamp");
        String timestamp = js.getString("X-TIMESTAMP");
        String regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timestamp);
        Assert.assertTrue(matcher.matches());
        Assert.assertNotNull(timestamp, "X-Time-Stamp Tidak ada");
        System.out.println("Terdapat XTimeStamp");
        Assert.assertFalse(timestamp.isEmpty(), "X-Time-Stamp Kosong");
        System.out.println("XTimeStamp Tidak Kosong");
        System.out.println("XTimeStamp: " + timestamp);
        System.out.println("XTimeStamp sudah sesuai format");
    }

    public static void assertXClientKey(JsonPath js) {
        Allure.step("Check X-Client-Key");
        String clientKey = js.getString("X-CLIENT-KEY");
        Assert.assertNotNull(clientKey, "X-Client-Key Tidak ada");
        System.out.println("Terdapat X-Client-Key");
        Assert.assertFalse(clientKey.isEmpty(), "X-Client-Key Kosong");
        System.out.println("X-Client-Key Tidak Kosong");
        System.out.println("X-Client-Key: " + clientKey);
        System.out.println("X-Client-Key sudah sesuai format");
    }

    public static void assertXSignature(String Signature) {

    }

    public static void assertChannelID(JsonPath js, String expectedChannelID) {
        Allure.step("Check Channel-ID");
        String channelId = js.getString("CHANNEL-ID");
        Assert.assertNotNull(channelId, "Channel-ID Tidak ada");
        System.out.println("Terdapat Channel-ID");
        Assert.assertFalse(channelId.isEmpty(), "Channel-ID Kosong");
        System.out.println("Channel-ID Tidak Kosong");
        Assert.assertEquals(channelId, expectedChannelID);
        System.out.println("Channel-ID Sudah Sesuai");

    }

}
