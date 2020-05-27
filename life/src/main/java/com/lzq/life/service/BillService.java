package com.lzq.life.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lzq.life.vo.PageInfo;
import com.lzq.echarts.Option;
import com.lzq.life.entity.BasCatalog;
import com.lzq.life.entity.BasDistrict;
import com.lzq.life.entity.BusBillTag;
import com.lzq.life.entity.BusBillTagExample;
import com.lzq.life.manager.BillManager;
import com.lzq.life.mapper.BasCatalogMapper;
import com.lzq.life.mapper.BasDistrictMapper;
import com.lzq.life.mapper.BusBillTagMapper;
import com.lzq.life.mapper.ext.BusBillExtMapper;
import com.lzq.life.vo.BillVO;

/**
 * 
 * @Title BillService.java
 * @Package com.lzq.life.service
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 21, 2020 8:46:55 PM
 * 
 */
@Component
public class BillService {

	@Autowired
	@Qualifier("busBillExtMapper")
	private BusBillExtMapper billMapper;

	@Autowired
	private BasCatalogMapper basCatalogMapper;

	@Autowired
	private BasDistrictMapper districtMapper;

	@Autowired
	private BusBillTagMapper billTagMapper;

	@Autowired
	private BillManager billManager;

	@Transactional
	public void save(BillVO bill) {
		BasDistrict district = districtMapper.selectByPrimaryKey(bill.getDistrictId());
		if (district != null) {
			bill.setDistrictName(district.getDistrictName());
		}
		BasCatalog catalog = basCatalogMapper.selectByPrimaryKey(bill.getCatalogId());
		bill.setCatalogName(catalog.getCatalogName());
		if (bill.getBillId() != null) {
			billMapper.updateByPrimaryKey(bill);
			BusBillTagExample billtagexample = new BusBillTagExample();
			billtagexample.createCriteria().andBillIdEqualTo(bill.getBillId());
			billTagMapper.deleteByExample(billtagexample);
		} else {
			billMapper.insert(bill);
		}
		if (bill.getTagIds() != null && bill.getTagIds().length > 0) {
			for (int i = 0; i < bill.getTagIds().length; i++) {
				if (bill.getTagIds()[i] != null) {
					BusBillTag record = new BusBillTag();
					record.setBillId(bill.getBillId());
					record.setTagId(bill.getTagIds()[i]);
					billTagMapper.insert(record);
				}
			}
		}
	}

	public List<BillVO> getBills(BillVO vo) {
		List<BillVO> voList = billMapper.selectBill(vo);
		return voList;
	}

	public BillVO getBill(Long id) {
		BillVO bill = billMapper.selectByPrimaryKey(id);
		if (bill.getTags() != null && bill.getTags().size() > 0) {
			Long[] tagIds = new Long[bill.getTags().size()];
			for (int i = 0; i < tagIds.length; i++) {
				tagIds[i] = bill.getTags().get(i).getTagId();
			}
			bill.setTagIds(tagIds);
		}
		return bill;
	}

	public Option getHouseChartBar(String start, String end) throws Exception {
		Option option = billManager.getHouseChartBar(start, end);
		return option;
	}

	public Option getHouseChartLine(String start, String end) throws Exception {
		Option option = billManager.getHouseChartLine(start, end);
		return option;
	}

	public Option getHouseChartPie(String start, String end) throws Exception {
		Option option = billManager.getHouseChartPie(start, end);
		return option;
	}

}
