package com.lzq.life.Api.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzq.echarts.Option;
import com.lzq.life.Api.BillApi;
import com.lzq.life.common.ResponseFactroy;
import com.lzq.life.service.BillService;
import com.lzq.life.vo.BaseResponse;
import com.lzq.life.vo.BillVO;

/**
 * 
 * @Title BillApiImpl.java
 * @Package com.lzq.life.Api.impl
 * @Description 账单接口实现类
 * @author lizhiqiang
 * @date May 21, 2020 8:39:19 PM
 * 
 */
@Component
public class BillApiImpl implements BillApi {

	@Autowired
	private BillService billService;

	public BaseResponse<?> save(BillVO bill) {
		billService.save(bill);
		return ResponseFactroy.createSuccessResponse();
	}

	public BaseResponse<List<BillVO>> getBills() {
		List<BillVO> list = billService.getBills();
		return ResponseFactroy.createSuccessResponse(list);
	}

	public BaseResponse<BillVO> getBill(Long id) {
		BillVO bill = billService.getBill(id);
		return ResponseFactroy.createSuccessResponse(bill);
	}

	public BaseResponse<Option> getHouseChartBar(String start, String end) throws Exception {
		Option option = billService.getHouseChartBar(start, end);
		return ResponseFactroy.createSuccessResponse(option);

	}

	@Override
	public BaseResponse<Option> getHouseChartLine(String start, String end) throws Exception {
		Option option = billService.getHouseChartLine(start, end);
		return ResponseFactroy.createSuccessResponse(option);
	}

	@Override
	public BaseResponse<Option> getHouseChartPie(String start, String end) throws Exception {
		Option option = billService.getHouseChartPie(start, end);
		return ResponseFactroy.createSuccessResponse(option);
	}
}
