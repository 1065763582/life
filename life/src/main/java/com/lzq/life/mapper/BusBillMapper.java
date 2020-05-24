package com.lzq.life.mapper;

import com.lzq.life.entity.BusBill;
import com.lzq.life.entity.BusBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusBillMapper {
    int deleteByExample(BusBillExample example);

    int deleteByPrimaryKey(Long billId);

    int insert(BusBill record);

    int insertSelective(BusBill record);

    List<BusBill> selectByExample(BusBillExample example);

    BusBill selectByPrimaryKey(Long billId);

    int updateByExampleSelective(@Param("record") BusBill record, @Param("example") BusBillExample example);

    int updateByExample(@Param("record") BusBill record, @Param("example") BusBillExample example);

    int updateByPrimaryKeySelective(BusBill record);

    int updateByPrimaryKey(BusBill record);
}