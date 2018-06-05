package com.mine.dao;

import com.mine.model.SystemSession;
import com.mine.model.SystemSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemSessionMapper {
    int countByExample(SystemSessionExample example);

    int deleteByExample(SystemSessionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SystemSession record);

    int insertSelective(SystemSession record);

    List<SystemSession> selectByExample(SystemSessionExample example);

    SystemSession selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SystemSession record, @Param("example") SystemSessionExample example);

    int updateByExample(@Param("record") SystemSession record, @Param("example") SystemSessionExample example);

    int updateByPrimaryKeySelective(SystemSession record);

    int updateByPrimaryKey(SystemSession record);
}