package com.learning.demo.Controller.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class ServerUtils {
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String AUTHORIZED = "AUTHORIZED";
    public static final String TWO_POINTS = ":";

    public static String encodeToBase64(String stringToEncode) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(stringToEncode.getBytes());
    }

    public static String decodeFromBase64(String encodedString) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return new String(base64Decoder.decodeBuffer(encodedString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int generateAsciiFromStrings(String string) {
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            result += (int) string.charAt(i);
        }
        return result;
    }
}
