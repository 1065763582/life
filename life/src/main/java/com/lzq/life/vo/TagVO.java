package com.lzq.life.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TagVO {
	private Long billId;

	private Long tagId;

	private String tagName;
	
	private String tagType;

}