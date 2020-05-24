package com.lzq.life.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @Title ResponseCodeEnum.java
 * @Package com.lzq.life.common
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 23, 2020 6:53:01 PM
 * 
 */
@AllArgsConstructor
@Getter
public enum ResponseCodeEnum {

	SUCCESS(20000, "成功"), ERROR(50000, "错误");

	private Integer code;
	private String msg;

}
