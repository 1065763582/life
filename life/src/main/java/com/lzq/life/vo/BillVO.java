package com.lzq.life.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzq.life.entity.BusBill;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * @Title BillVO.java
 * @Package com.lzq.life.vo
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 17, 2020 8:30:35 PM
 * 
 */
@Data
@ToString
public class BillVO extends BusBill {

	private List<TagVO> tags;

	private String cdateFormat;

	private Long[] tagIds;

	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime cdate;

	// ---------- 用于表格界面 搜索功能传参-----------
	private Long tagId;

	private String startDate;
	
	private String endDate;
	
	private Integer pageNum;
	
	private Integer pageSize;

	// ---------- 用于表格界面 搜索功能传参 END-----------
}
