package com.lzq.life.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzq.echarts.Option;
import com.lzq.life.vo.BaseResponse;

/**
 * 
 * @Title WealthApi.java
 * @Package com.lzq.life.Api
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 27, 2020 8:57:06 PM
 * 
 */
@RestController
@RequestMapping("wealth")
public interface WealthApi {
	@GetMapping("pie")
	public BaseResponse<Option> getWealthChartPie() throws Exception;

}
