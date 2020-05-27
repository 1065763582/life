package com.lzq.life.vo;

import java.util.List;

import lombok.Data;

/**  
 * 
 * @Title PageInfo.java   
 * @Package com.lzq.life.vo   
 * @Description TODO(用一句话描述该文件做什么)   
 * @author lizhiqiang     
 * @date May 26, 2020 1:16:06 PM
 * 
 */
@Data
public class PageInfo<T> {
	
	private Integer pageNum;
	private Integer pageSize;
	private Integer startRow;
	private Integer total;
	private List<T> list;
	
	

}
