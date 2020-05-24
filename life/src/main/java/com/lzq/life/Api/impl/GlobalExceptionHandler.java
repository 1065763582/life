package com.lzq.life.Api.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzq.life.common.ResponseFactroy;
import com.lzq.life.vo.BaseResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Title GlobalExceptionHandler.java
 * @Package com.lzq.life.Api.impl
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 23, 2020 7:03:16 PM
 * 
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public BaseResponse<?> globalException(HttpServletResponse response, Exception ex) {
		log.error("错误代码: " + response.getStatus(), ex);
		return ResponseFactroy.createErrorResponse(ex.getMessage());
	}

}
