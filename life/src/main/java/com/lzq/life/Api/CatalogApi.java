package com.lzq.life.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzq.life.entity.BasCatalog;
import com.lzq.life.entity.BasCatalogExample;
import com.lzq.life.mapper.BasCatalogMapper;
import com.lzq.life.vo.BaseResponse;

/**  
 * 
 * @Title CatalogApi.java   
 * @Package com.lzq.life.Api   
 * @Description TODO(用一句话描述该文件做什么)   
 * @author lizhiqiang     
 * @date May 16, 2020 5:05:46 PM
 * 
 */
@RestController
@RequestMapping("catalog")
public class CatalogApi {

	@Autowired
	private BasCatalogMapper basCatalog;
	
	@GetMapping
	public BaseResponse<List<BasCatalog>> getCodes(String group){
		BaseResponse<List<BasCatalog>> resp = new BaseResponse<>();
		resp.setCode(20000);
		BasCatalogExample example = new BasCatalogExample();
		example.createCriteria().andParentIdNotEqualTo(-1L);
		List<BasCatalog> list = basCatalog.selectByExample(example);
		resp.setData(list);
		return resp;
	}
	
}
