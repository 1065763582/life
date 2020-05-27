package com.lzq.life.service;

import org.springframework.stereotype.Component;

import com.lzq.echarts.Option;
import com.lzq.life.manager.WealthManager;

/**
 * 
 * @Title WealthService.java
 * @Package com.lzq.life.service
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 27, 2020 8:59:56 PM
 * 
 */
@Component
public class WealthService {

	private WealthManager wealthManager;

	public Option getWealthChartPie() throws Exception {
		Option option = wealthManager.getWealthChartPie();
		return option;
	}

}
