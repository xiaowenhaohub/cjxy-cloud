package com.changji.cloud.website.service;

import com.changji.cloud.website.dto.QueryCourseDTO;
import com.changji.cloud.website.model.WebsiteUser;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 12:59 2022/8/20
 * @ Modified By：
 */
public interface CourseService {

    /**
     * 获取当前用户课表
     * @param queryCourseDTO
     */
    void getMyCourseList(QueryCourseDTO queryCourseDTO);

}
