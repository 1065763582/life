package com.lzq.life.entity;

import lombok.Data;

/**
 * Table: bas_district
 */
@Data
public class BasDistrict {
    /**
     * 主键自增
     *
     * Table:     bas_district
     * Column:    district_id
     * Nullable:  false
     */
    private Integer districtId;

    /**
     * 父类id
     *
     * Table:     bas_district
     * Column:    pid
     * Nullable:  true
     */
    private Integer pid;

    /**
     * 城市的名字
     *
     * Table:     bas_district
     * Column:    district_name
     * Nullable:  true
     */
    private String districtName;

    /**
     * 城市的类型，0是国，1是省，2是市，3是区
     *
     * Table:     bas_district
     * Column:    type
     * Nullable:  true
     */
    private Integer type;

    /**
     * 地区所处的层级
     *
     * Table:     bas_district
     * Column:    hierarchy
     * Nullable:  true
     */
    private Integer hierarchy;

    /**
     * 层级序列
     *
     * Table:     bas_district
     * Column:    district_sqe
     * Nullable:  true
     */
    private String districtSqe;
}