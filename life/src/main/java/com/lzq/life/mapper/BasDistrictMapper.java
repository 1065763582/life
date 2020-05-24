package com.lzq.life.mapper;

import com.lzq.life.entity.BasDistrict;
import com.lzq.life.entity.BasDistrictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasDistrictMapper {
    int deleteByExample(BasDistrictExample example);

    int deleteByPrimaryKey(Integer districtId);

    int insert(BasDistrict record);

    int insertSelective(BasDistrict record);

    List<BasDistrict> selectByExample(BasDistrictExample example);

    BasDistrict selectByPrimaryKey(Integer districtId);

    int updateByExampleSelective(@Param("record") BasDistrict record, @Param("example") BasDistrictExample example);

    int updateByExample(@Param("record") BasDistrict record, @Param("example") BasDistrictExample example);

    int updateByPrimaryKeySelective(BasDistrict record);

    int updateByPrimaryKey(BasDistrict record);
}