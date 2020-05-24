package com.lzq.life.mapper;

import com.lzq.life.entity.BasTag;
import com.lzq.life.entity.BasTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasTagMapper {
    int deleteByExample(BasTagExample example);

    int deleteByPrimaryKey(Long tagId);

    int insert(BasTag record);

    int insertSelective(BasTag record);

    List<BasTag> selectByExample(BasTagExample example);

    BasTag selectByPrimaryKey(Long tagId);

    int updateByExampleSelective(@Param("record") BasTag record, @Param("example") BasTagExample example);

    int updateByExample(@Param("record") BasTag record, @Param("example") BasTagExample example);

    int updateByPrimaryKeySelective(BasTag record);

    int updateByPrimaryKey(BasTag record);
}