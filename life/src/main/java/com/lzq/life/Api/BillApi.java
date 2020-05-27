package com.lzq.life.Api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzq.echarts.Option;
import com.lzq.life.vo.BaseResponse;
import com.lzq.life.vo.BillVO;

/**
 * 
 * @Title BillApi.java
 * @Package com.lzq.life.Api
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 16, 2020 4:38:39 PM
 * 
 */
@RestController
@RequestMapping("bill")
public interface BillApi {

	@PostMapping
	public BaseResponse<?> save(@RequestBody BillVO bill);

	@GetMapping
	public BaseResponse<List<BillVO>> getBills(BillVO vo);

	@GetMapping("/{id}")
	public BaseResponse<BillVO> getBill(@PathVariable Long id);

	@GetMapping("/houseChart/line")
	public BaseResponse<Option> getHouseChartLine(String start, String end) throws Exception;

	@GetMapping("/houseChart/bar")
	public BaseResponse<Option> getHouseChartBar(String start, String end) throws Exception;

	@GetMapping("/houseChart/pie")
	public BaseResponse<Option> getHouseChartPie(String start, String end) throws Exception;
}
