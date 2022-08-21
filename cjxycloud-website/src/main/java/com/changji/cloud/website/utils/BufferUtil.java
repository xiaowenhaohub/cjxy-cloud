package com.changji.cloud.website.utils;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 16:36 2022/8/21
 * @ Modified By：
 */
public class BufferUtil {

    public static String inputToString(CloseableHttpResponse response) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        } catch (Exception e) {
            throw new RuntimeException("buffer to string error");
        }

        StringBuffer sb = new StringBuffer("");
        String line = "";
        String NL = System.getProperty("line.separator");
        while (true) {
            try {
                if (!((line = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line + NL);
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
