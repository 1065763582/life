package com.lzq.life.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzq.life.entity.BasDistrict;
import com.lzq.life.entity.BasDistrictExample;
import com.lzq.life.mapper.BasDistrictMapper;
import com.lzq.life.vo.BaseResponse;

/**  
 * 
 * @Title DistrictApi.java   
 * @Package com.lzq.life.Api   
 * @Description TODO(用一句话描述该文件做什么)   
 * @author lizhiqiang     
 * @date May 18, 2020 9:26:26 AM
 * 
 */
@RestController
@RequestMapping("district")
public class DistrictApi {

	@Autowired
	private BasDistrictMapper distractMapper;
	
	@GetMapping
	public BaseResponse<List<BasDistrict>> getCodes(String group){
		BaseResponse<List<BasDistrict>> resp = new BaseResponse<>();
		resp.setCode(20000);
		BasDistrictExample example = new BasDistrictExample();
		List<BasDistrict> list = distractMapper.selectByExample(example);
		resp.setData(list);
		return resp;
	}
}
