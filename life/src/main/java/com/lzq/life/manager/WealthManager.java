package com.lzq.life.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzq.echarts.Option;
import com.lzq.echarts.code.RoseType;
import com.lzq.echarts.factroy.EchartsOptionFactory;
import com.lzq.echarts.series.Pie;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Title WealthManager.java
 * @Package com.lzq.life.manager
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 27, 2020 9:02:16 PM
 * 
 */
@Component
@Slf4j
public class WealthManager {
	@Autowired
	DataSource dataSource;

	public Option getWealthChartPie() throws Exception {
		Option op = null;
		try (Connection c = dataSource.getConnection()) {
			String sql = getWealthChartPieSql();
			PreparedStatement state = c.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			op = EchartsOptionFactory.createPieOption("Property", rs);
		}
		return op;
	}

	private String getWealthChartPieSql() {
		// 按时间和类型统计
		SQL sql = new SQL().SELECT("b.economic_source_name `catalog_name`, sum(a.money) `value` ");
		sql.FROM("bus_monthly_wealth a").LEFT_OUTER_JOIN("bas_economic_source b on a.economic_source_id = b.economic_source_id")
		.GROUP_BY("b.economic_source_name, a.record_date");
		String result = sql.toString();
		log.info(result);
		return result;
	}

}
