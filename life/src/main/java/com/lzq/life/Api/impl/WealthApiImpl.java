package com.lzq.life.Api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzq.echarts.Option;
import com.lzq.life.Api.WealthApi;
import com.lzq.life.common.ResponseFactroy;
import com.lzq.life.service.WealthService;
import com.lzq.life.vo.BaseResponse;

/**  
 * 
 * @Title WealthApiImpl.java   
 * @Package com.lzq.life.Api.impl   
 * @Description TODO(用一句话描述该文件做什么)   
 * @author lizhiqiang     
 * @date May 27, 2020 8:58:14 PM
 * 
 */
@Component
public class WealthApiImpl implements WealthApi{

	@Autowired
	private WealthService wealthService;
	
	@Override
	public BaseResponse<Option> getWealthChartPie() throws Exception {
		Option option = wealthService.getWealthChartPie();
		return ResponseFactroy.createSuccessResponse(option);
	}

}
