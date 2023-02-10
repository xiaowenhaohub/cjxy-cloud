package com.changji.cloud.website.mapper;

import com.changji.cloud.website.model.InstituteInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:19 2022/8/23
 * @ Modified By：
 */
@Repository
public interface InstituteInfoMapper {

    /**
     * 根据学院名称查询学院编号
     * @param institute
     * @return
     */
    InstituteInfo queryIdByInstitute(@Param("institute") String institute);

}
