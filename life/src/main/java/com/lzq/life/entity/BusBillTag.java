package com.lzq.life.entity;

import lombok.Data;

/**
 * Table: bus_bill_tag
 */
@Data
public class BusBillTag {
    /**
     * Table:     bus_bill_tag
     * Column:    bill_id
     * Nullable:  false
     */
    private Long billId;

    /**
     * Table:     bus_bill_tag
     * Column:    tag_id
     * Nullable:  false
     */
    private Long tagId;
}