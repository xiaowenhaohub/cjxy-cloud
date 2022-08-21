package com.changji.cloud.website.controller;

import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.website.dto.QueryCourseDTO;
import com.changji.cloud.website.model.Course;
import com.changji.cloud.website.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:30 2022/8/21
 * @ Modified By：
 */
@RestController()
@Api("课表")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("course/get")
    @RequiresPermissions("system:course:query")
    public ServerResponseEntity<Map<Integer, Course>> get(@RequestBody QueryCourseDTO queryCourseDTO) {
        System.out.println(queryCourseDTO);
        courseService.getMyCourseList(queryCourseDTO);
        return null;
    }

}
