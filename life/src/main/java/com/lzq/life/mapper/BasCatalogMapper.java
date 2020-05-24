package com.lzq.life.mapper;

import com.lzq.life.entity.BasCatalog;
import com.lzq.life.entity.BasCatalogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasCatalogMapper {
    int deleteByExample(BasCatalogExample example);

    int deleteByPrimaryKey(Long catalogId);

    int insert(BasCatalog record);

    int insertSelective(BasCatalog record);

    List<BasCatalog> selectByExample(BasCatalogExample example);

    BasCatalog selectByPrimaryKey(Long catalogId);

    int updateByExampleSelective(@Param("record") BasCatalog record, @Param("example") BasCatalogExample example);

    int updateByExample(@Param("record") BasCatalog record, @Param("example") BasCatalogExample example);

    int updateByPrimaryKeySelective(BasCatalog record);

    int updateByPrimaryKey(BasCatalog record);
}