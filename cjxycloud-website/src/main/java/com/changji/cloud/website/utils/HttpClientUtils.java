package com.changji.cloud.website.utils;

import org.apache.http.Consts;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 19:58 2022/4/22
 * @ Modified By：
 */
public class HttpClientUtils {

    /**
     * 普通 SSL证书
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault() {
        return HttpClients.custom().setSSLSocketFactory(getSSL()).build();

    }

    /**
     * 自定义cookie SSL证书
     * @param cookieStore
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault(CookieStore cookieStore) {
        return HttpClients.custom().setSSLSocketFactory(getSSL()).setDefaultCookieStore(cookieStore).build();

    }

    /**
     * 获取SSL
     * @return
     */
    public static SSLConnectionSocketFactory getSSL() {
        try {
            //使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            //NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何
            //有效的SSL会话并匹配到目标主机。
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        } catch (Exception e) {
            throw new RuntimeException("SSL未知异常");
        }
    }

    /**
     * 根据url和 list获取post
     * @param list
     * @param url
     * @return
     */
    public static HttpPost getHttpPost (List<NameValuePair> list, String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
        httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
        httpPost.setEntity(formEntity);
        return httpPost;
    }
}
