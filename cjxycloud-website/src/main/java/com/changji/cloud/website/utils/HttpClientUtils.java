package com.changji.cloud.website.utils;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.response.ResponseEnum;
import com.changji.cloud.website.constant.HttpConstants;
import org.apache.http.Consts;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
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
        return createSSLClientCustom(null);
    }

    /**
     * 自定义cookie SSL证书
     * @param cookieStore
     * @return
     */
    public static CloseableHttpClient createSSLClientCustom(CookieStore cookieStore) {
        return HttpClients.custom().setSSLSocketFactory(getSSL()).setDefaultCookieStore(cookieStore).build();
    }

    /**
     * 根据url获取post
     * @param url
     * @return
     */
    public static HttpPost getHttpPost (String url) {
        return getHttpPost(new ArrayList<>(), url);
    }


    /**
     * 根据url和 list获取post
     * @param list
     * @param url
     * @return
     */
    public static HttpPost getHttpPost (List<NameValuePair> list, String url) {
        HttpPost httpPost = new HttpPost(HttpConstants.BASE_URL.value() + url);
        httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
        httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
        httpPost.setEntity(formEntity);
        return httpPost;
    }

    /**
     * 发起http请求并返回response
     * @param httpClient
     * @param httpPost
     * @return
     */
    public static CloseableHttpResponse getResponse(CloseableHttpClient httpClient, HttpPost httpPost ) {
       return getResponse(null,httpClient,httpPost);
    }


    public static CloseableHttpResponse getResponse(String url,  CookieStore cookieStore, List<NameValuePair> list, HttpClientContext context ) {
        HttpPost httpPost = getHttpPost(list, url);
        CloseableHttpClient httpClient = createSSLClientCustom(cookieStore);
        return getResponse(context, httpClient, httpPost);
    }

    public static CloseableHttpResponse getResponse(String url,  CookieStore cookieStore) {
        return getResponse(url, cookieStore, new ArrayList<>(),null);
    }

    /**
     * 发起http请求并返回response
     * @param context
     * @param httpClient
     * @param httpPost
     * @return
     */
    public static CloseableHttpResponse getResponse(HttpClientContext context, CloseableHttpClient httpClient, HttpPost httpPost) {
        try {
            return httpClient.execute(httpPost, context);
        } catch (IOException e) {
            throw new ServiceException("HTTP 请求异常", ResponseEnum.EXCEPTION);
        }
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
            //SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        } catch (Exception e) {
            throw new RuntimeException("SSL未知异常");
        }
    }


    //    public static class Builder {
//
//        private CloseableHttpClient httpClient;
//
//        private HttpPost httpPost;
//
//        private HttpClientContext context;
//
//        public Builder() {
//
//        }
//
//        public Builder setHttpClient(CookieStore cookieStore) {
//            this.httpClient = HttpClients.custom().setSSLSocketFactory(getSSL()).setDefaultCookieStore(cookieStore).build();
//            return this;
//        }
//
//        public Builder setHttpClient() {
//            return setHttpClient(null);
//        }
//
//        public Builder setHttpPost(List<NameValuePair> list, String url) {
//            HttpPost httpPost = new HttpPost(HttpConstants.BASE_URL.value() + url);
//            httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
//            httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
//            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
//            httpPost.setEntity(formEntity);
//            this.httpPost = httpPost;
//            return this;
//        }
//
//        public Builder setHttpContext(HttpClientContext context){
//            this.context = context;
//            return this;
//        }
//
//        public CloseableHttpResponse builder() {
//            try {
//                return httpClient.execute(httpPost,context);
//            } catch (IOException e) {
//                throw new ServiceException("HTTP 请求异常", ResponseEnum.EXCEPTION);
//            }
//        }
//
//
//
//        public SSLConnectionSocketFactory getSSL() {
//        try {
//            //使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
//            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//                // 信任所有
//                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                    return true;
//                }
//            }).build();
//            //NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何
//            //有效的SSL会话并匹配到目标主机。
//            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//            //SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//            return new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//        } catch (Exception e) {
//            throw new RuntimeException("SSL未知异常");
//        }
//    }
//
//    }
}
