package com.lzq.life.vo;

import lombok.Data;

/**  
 * 
 * @Title BaseResponse.java   
 * @Package com.lzq.life.vo   
 * @Description 统一返回类型  
 * @author lizhiqiang     
 * @date May 16, 2020 4:39:57 PM
 * 
 */

@Data
public class BaseResponse<T> {
	private Integer code;
	private T data;
	private String message;
}
