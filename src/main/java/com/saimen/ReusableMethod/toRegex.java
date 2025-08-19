package com.saimen.ReusableMethod;

public class toRegex {
    public static String toRegexFormat(String code) {
        // Kode selalu 7 karakter
        return "^" + code.substring(0, 3) + ".." + code.substring(5) + "$";
    }
}
