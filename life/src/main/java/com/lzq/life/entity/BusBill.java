package com.lzq.life.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Table: bus_bill
 */
@Data
public class BusBill {
    /**
     * Table:     bus_bill
     * Column:    bill_id
     * Nullable:  false
     */
    private Long billId;

    /**
     * 消费时间
     *
     * Table:     bus_bill
     * Column:    cdate
     * Nullable:  true
     */
    private LocalDateTime cdate;

    /**
     * 金额
     *
     * Table:     bus_bill
     * Column:    money
     * Nullable:  true
     */
    private BigDecimal money;

    /**
     * 描述
     *
     * Table:     bus_bill
     * Column:    desc
     * Nullable:  true
     */
    private String desc;

    /**
     * 类别
     *
     * Table:     bus_bill
     * Column:    catalog_id
     * Nullable:  true
     */
    private Long catalogId;

    /**
     * Table:     bus_bill
     * Column:    catalog_name
     * Nullable:  true
     */
    private String catalogName;

    /**
     * 消费人
     *
     * Table:     bus_bill
     * Column:    who
     * Nullable:  true
     */
    private String who;

    /**
     * 地区ID
     *
     * Table:     bus_bill
     * Column:    district_id
     * Nullable:  true
     */
    private Integer districtId;

    /**
     * Table:     bus_bill
     * Column:    district_name
     * Nullable:  true
     */
    private String districtName;

    /**
     * 图片路径
     *
     * Table:     bus_bill
     * Column:    img
     * Nullable:  true
     */
    private String img;
}