package com.lzq.life.mapper;

import com.lzq.life.entity.BusBillTag;
import com.lzq.life.entity.BusBillTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusBillTagMapper {
    int deleteByExample(BusBillTagExample example);

    int insert(BusBillTag record);

    int insertSelective(BusBillTag record);

    List<BusBillTag> selectByExample(BusBillTagExample example);

    int updateByExampleSelective(@Param("record") BusBillTag record, @Param("example") BusBillTagExample example);

    int updateByExample(@Param("record") BusBillTag record, @Param("example") BusBillTagExample example);
}