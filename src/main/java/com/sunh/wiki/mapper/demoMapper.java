package com.sunh.wiki.mapper;

import com.sunh.wiki.domain.demo;
import com.sunh.wiki.domain.demoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface demoMapper {
    long countByExample(demoExample example);

    int deleteByExample(demoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(demo record);

    int insertSelective(demo record);

    List<demo> selectByExample(demoExample example);

    demo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") demo record, @Param("example") demoExample example);

    int updateByExample(@Param("record") demo record, @Param("example") demoExample example);

    int updateByPrimaryKeySelective(demo record);

    int updateByPrimaryKey(demo record);
}