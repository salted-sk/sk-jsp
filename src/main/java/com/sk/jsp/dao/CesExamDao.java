package com.sk.jsp.dao;

import com.base.mapper.MyMapper;
import com.sk.jsp.entity.CesExam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评价方案Dao层
 *
 * @author zhangqiao
 * @since 2019-01-09 11:42:20
 */
public interface CesExamDao extends MyMapper<CesExam> {

    //获取评价列表
    List<Map> getExamList(@Param("exam") CesExam exam, @Param("bmsJdbcName") String bmsJdbcName);

}