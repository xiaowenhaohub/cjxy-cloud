package com.changji.cloud.website.controller;

import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.website.dto.QueryCourseDTO;
import com.changji.cloud.website.model.Lesson;
import com.changji.cloud.website.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public ServerResponseEntity<List<List<Lesson>>> getAccountCourse(@Validated @RequestBody QueryCourseDTO queryCourseDTO) {

        List<List<Lesson>> courseList = courseService.getMyCourseList(queryCourseDTO);
        return ServerResponseEntity.success(courseList);
    }

}
