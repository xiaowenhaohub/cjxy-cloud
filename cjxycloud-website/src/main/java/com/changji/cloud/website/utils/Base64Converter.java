package com.changji.cloud.website.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:38 2022/8/20
 * @ Modified By：BASE 64 加密
 */
public class Base64Converter {
    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Encoder decoder = Base64.getEncoder();

    public static String encode(String text)  {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }
}
