package com.saimen.ReusableMethod;

import io.restassured.path.json.JsonPath;

public class parsingJson {

    public static JsonPath rawToJson(String response) {
        JsonPath js = new JsonPath(response);
        return js;
    }

}
