package com.changji.cloud.website.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.changji.cloud.common.core.exception.NullParamException;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.auth.AuthUtil;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.website.constant.HttpConstants;
import com.changji.cloud.website.dto.QueryCourseDTO;
import com.changji.cloud.website.mapper.CoursesMapper;
import com.changji.cloud.website.model.Courses;
import com.changji.cloud.website.model.Lesson;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import com.changji.cloud.website.service.CourseService;
import com.changji.cloud.website.utils.BufferUtil;
import com.changji.cloud.website.utils.HTMLUtil;
import com.changji.cloud.website.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import netscape.javascript.JSObject;
import org.apache.catalina.security.SecurityUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 13:04 2022/8/20
 * @ Modified By：
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CookieService cookieService;

    @Autowired
    private CoursesMapper coursesMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public List<List<Lesson>> getMyCourseList(QueryCourseDTO queryCourseDTO) {

        //从thread_local获取用户名和密码
        LoginUser loginUser = AuthUtil.getLoginUser();
        WebsiteUser user = new WebsiteUser();
        user.setAccount(loginUser.getAccount());
        user.setPassword(SecurityUtils.getPassword());
        /**
         * 先从从本地数据库查询课程信息
         */
        List<Courses> coursesList = coursesMapper.queryByInstituteAndClasses(queryCourseDTO);
        if (StringUtils.isNotEmpty(coursesList)) {
            log.info("本地查询课程");
            return JsonToCourseList(coursesList.get(0).getCourseInfo());
        }


        //获取教务管理系统cookie
        CookieStore cookieStore = cookieService.getCookie(user);
        String url = HttpConstants.MY_COURSE_URL.value();
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("cj0701id", ""));
        list.add(new BasicNameValuePair("zc",queryCourseDTO.getWeekly()));//周次
        list.add(new BasicNameValuePair("demo",""));
        list.add(new BasicNameValuePair("xnxq01id",queryCourseDTO.getSemester()));//学年学期
        list.add(new BasicNameValuePair("sfFD",""));
        list.add(new BasicNameValuePair("wkbkc","1"));//无课表课程
        list.add(new BasicNameValuePair("kbjcmsid","A63DB2E690D94F43945978F87DBE514D"));//默认节次模式

        //发起http请求 从官网查询课程
        CloseableHttpResponse response = HttpClientUtils.getResponse(url,cookieStore,list,null);
        String context = BufferUtil.inputToString(response);

        List<List<Lesson>> courseList = HTMLUtil.getCourseList(context);

        //保存到本地数据库
        String courseInfo = JSONObject.toJSONString(courseList);
        Courses courses = mapperFacade.map(queryCourseDTO, Courses.class);
        courses.setCourseInfo(courseInfo);
        courses.setStatus(1);
        coursesMapper.save(courses);
        return courseList;

    }

    public List<List<Lesson>> JsonToCourseList(String courseInfo) {

        List<List<Lesson>> courseList = new ArrayList<>();

        List<String> list1 =  JSONObject.parseObject(courseInfo, new TypeReference<ArrayList<String>>(){});
        list1.forEach(row -> {
            List<Lesson> lessonRow = JSON.parseArray(row, Lesson.class);
            courseList.add(lessonRow);
        });
        return courseList;
    }
}
