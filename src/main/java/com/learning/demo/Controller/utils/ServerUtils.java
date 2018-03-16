package com.learning.demo.Controller.utils;

import sun.misc.BASE64Encoder;

public class ServerUtils {
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String AUTHORIZED = "AUTHORIZED";
    public static final String TWO_POINTS = ":";

    public static String encodeToBase64(String stringToEncode) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(stringToEncode.getBytes());
    }
}
