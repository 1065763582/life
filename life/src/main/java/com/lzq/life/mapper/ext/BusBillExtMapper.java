package com.lzq.life.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzq.life.mapper.BusBillMapper;
import com.lzq.life.vo.BillVO;

/**
 * 
 * @Title BusBillExtMapper.java
 * @Package com.lzq.life.mapper
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 17, 2020 3:12:32 PM
 * 
 */
public interface BusBillExtMapper extends BusBillMapper {

	List<BillVO> selectBill(@Param("vo") BillVO vo);

	BillVO selectByPrimaryKey(Long billId);
}
