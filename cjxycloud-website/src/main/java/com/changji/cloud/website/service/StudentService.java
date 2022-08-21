package com.changji.cloud.website.service;

import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.website.model.StudentInfo;
import org.apache.http.client.CookieStore;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 23:16 2022/8/21
 * @ Modified By：
 */
public interface StudentService {

    AuthAccountVO getStudentInfo(CookieStore cookieStore);
}
