package com.changji.cloud.website.utils;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.response.ResponseEnum;
import com.changji.cloud.common.core.text.Convert;
import com.changji.cloud.website.constant.HttpConstants;
import com.changji.cloud.website.model.WebsiteUser;
import org.apache.http.Consts;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:26 2022/8/20
 * @ Modified By：
 */
public class CookieUtil {

    /**
     * 获取cookie
     * @param user
     * @return
     */
    public static CookieStore getCookieStore(WebsiteUser user) {
        String url = HttpConstants.BASE_URL.value() + HttpConstants.LOGIN_URL.value();
        HttpClientContext context = new HttpClientContext();
        CloseableHttpResponse response = getResponse(context, url, user);
        if(response.getStatusLine().getStatusCode() != 302){
            throw  new ServiceException("账号或密码错误", ResponseEnum.ACCOUNT_ERROR);
        }
        return context.getCookieStore();
    }


    /**
     * 获取response
     * @param context
     * @param url
     * @param user
     * @return
     */
    public static CloseableHttpResponse getResponse(HttpClientContext context, String url, WebsiteUser user) {
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = getHttpPost(user, url);
        try {
            return httpClient.execute(httpPost, context);
        } catch (IOException e) {
            throw new ServiceException("获取cookie 异常", ResponseEnum.EXCEPTION);
        }
    }

    public static CloseableHttpClient getHttpClient() {
        return HttpClientUtils.createSSLClientDefault();
    }

    /**
     * 配置POST请求
     * @param user
     * @param url
     * @return
     */
    public static HttpPost getHttpPost(WebsiteUser user, String url) {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("loginMethod","LoginToXk"));
        list.add(new BasicNameValuePair("userAccount",getEncodeString(user.getAccount())));
        list.add(new BasicNameValuePair("userPassword",getEncodeString(user.getPassword())));
        list.add(new BasicNameValuePair("encoded",getEncoded(user)));
        return HttpClientUtils.getHttpPost(list, url);
    }

    /**
     * 获取encode
     * @param user
     * @return
     */
    public static String getEncoded(WebsiteUser user) {
        return getEncodeString(user.getAccount()) + "%%%" + getEncodeString(user.getPassword());
    }


    /**
     * 加密字符串 base 64
     * @param encode
     * @return
     */
    public static String getEncodeString(String encode) {
        return Base64Converter.encode(encode);
    }

}
