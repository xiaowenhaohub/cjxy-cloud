package com.changji.cloud.website.mapper;

import com.changji.cloud.website.dto.QueryCourseDTO;
import com.changji.cloud.website.model.Courses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 21:53 2022/8/22
 * @ Modified By：
 */
public interface CoursesMapper {

    /**
     * 保存课表信息
     * @param courses
     */
    void save(@Param("courses") Courses courses);


    /**
     * 根据 院校 班级 学期 周次 查询课表
     * @param queryCourseDTO
     * @return
     */
    List<Courses> queryByInstituteAndClasses(@Param("queryCourseDTO") QueryCourseDTO queryCourseDTO);
}
