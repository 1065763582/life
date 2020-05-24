package com.lzq.life.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzq.life.entity.BasTag;
import com.lzq.life.entity.BasTagExample;
import com.lzq.life.mapper.BasTagMapper;
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
@RequestMapping("tag")
public class TagApi {

	@Autowired
	private BasTagMapper tagMapper;
	
	@GetMapping
	public BaseResponse<List<BasTag>> getCodes(String group){
		BaseResponse<List<BasTag>> resp = new BaseResponse<>();
		resp.setCode(20000);
		BasTagExample example = new BasTagExample();
		List<BasTag> list = tagMapper.selectByExample(example);
		resp.setData(list);
		return resp;
	}
}
