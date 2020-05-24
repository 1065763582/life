package com.lzq.life.entity;

import lombok.Data;

/**
 * Table: bas_catalog
 */
@Data
public class BasCatalog {
    /**
     * 代码
     *
     * Table:     bas_catalog
     * Column:    catalog_id
     * Nullable:  false
     */
    private Long catalogId;

    /**
     * 父级代码
     *
     * Table:     bas_catalog
     * Column:    parent_id
     * Nullable:  true
     */
    private Long parentId;

    /**
     * 名称
     *
     * Table:     bas_catalog
     * Column:    catalog_name
     * Nullable:  false
     */
    private String catalogName;

    /**
     * 代码层级，从0开始
     *
     * Table:     bas_catalog
     * Column:    level
     * Nullable:  true
     */
    private Integer level;
}