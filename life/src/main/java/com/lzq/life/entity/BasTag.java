package com.lzq.life.entity;

import lombok.Data;

/**
 * Table: bas_tag
 */
@Data
public class BasTag {
    /**
     * Table:     bas_tag
     * Column:    tag_id
     * Nullable:  false
     */
    private Long tagId;

    /**
     * Table:     bas_tag
     * Column:    tag_name
     * Nullable:  false
     */
    private String tagName;

    /**
     * Table:     bas_tag
     * Column:    tag_type
     * Nullable:  true
     */
    private String tagType;
}