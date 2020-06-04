package com.lzq.life.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lzq.echarts.Option;
import com.lzq.echarts.code.MarkType;
import com.lzq.echarts.code.SeriesType;
import com.lzq.echarts.data.Data;
import com.lzq.echarts.factroy.EchartsOptionFactory;
import com.lzq.echarts.series.MarkLine;
import com.lzq.echarts.style.LineStyle;
import com.lzq.life.mapper.ext.BusBillExtMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Title BillManager.java
 * @Package com.lzq.life.manager
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 21, 2020 9:26:51 PM
 * 
 */
@Component
@Slf4j
public class BillManager {

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("busBillExtMapper")
	private BusBillExtMapper billMapper;

	public Option getHouseChartBar(String start, String end) throws Exception {
		Option op = null;
		try (Connection c = dataSource.getConnection()) {
			String sql = getHouseBarSql(start, end);
			PreparedStatement state = c.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			op = EchartsOptionFactory.createOption("房租总计", rs);
			op.getSeries().stream().filter(seri -> seri.getType().equals(SeriesType.line)).findFirst()
					.ifPresent(seri -> seri.markLine(new MarkLine().lineStyle(new LineStyle().color("#C65A5A"))
							.data(new Data().type(MarkType.average).name("平均值"))));
		}
		return op;
	}

	private String getHouseBarSql(String start, String end) {
		// 按时间和类型统计
		SQL sql = new SQL().SELECT("a.catalog_name, 'PART' stack_name, 'BAR' type_name");
		// 按时间统计
		SQL totalSQL = new SQL().SELECT("c.catalog_name, 'TOTAL' stack_name, 'LINE' type_name");
		List<String> dateRange = getBetweenDate(start, end);
		for (String date : dateRange) {
			String colNmae = "xAxis_" + date;
			sql.SELECT("sum(case DATE_FORMAT(a.cdate,'%Y-%m-%d') when '" + date + "' then a.money else 0 end) '"
					+ colNmae + "'");
			totalSQL.SELECT("sum(case DATE_FORMAT(c.cdate,'%Y-%m-%d') when '" + date + "' then c.money else 0 end) '"
					+ colNmae + "'");
		}
		sql.FROM("bus_bill a").LEFT_OUTER_JOIN("bas_catalog b on a.catalog_id = b.catalog_id").WHERE("b.parent_id = 6")
				.WHERE("a.cdate >= '" + start + "'").WHERE("a.cdate <= '" + end + "'").GROUP_BY("a.catalog_name");
		SQL innsersql = new SQL() {
			{
				SELECT("a.cdate, sum(a.money) money, '总计'  catalog_name");
				FROM("bus_bill a");
				LEFT_OUTER_JOIN("bas_catalog b on a.catalog_id = b.catalog_id");
				WHERE("b.parent_id = 6");
				WHERE("a.cdate >= '" + start + "'");
				WHERE("a.cdate <= '" + end + "'");
				GROUP_BY("a.cdate");
			}
		};
		totalSQL.FROM("(" + innsersql.toString() + ") c").GROUP_BY("c.catalog_name");

		String result = "select t.* from ( (" + sql.toString() + ") union all (" + totalSQL.toString()
				+ ") ) t ORDER BY FIELD(t.catalog_name, '房租','水费','电费','家电','总计')";
		log.info(result);
		return result;
	}

	public Option getHouseChartLine(String start, String end) throws Exception {
		Option op = null;
		try (Connection c = dataSource.getConnection()) {
			String sql = getHouseLineSql(start, end);
			PreparedStatement state = c.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			op = EchartsOptionFactory.createOption("物业", rs);
		}
		return op;
	}

	private String getHouseLineSql(String start, String end) {
		// 按时间和类型统计
		SQL sql = new SQL().SELECT("a.catalog_name, null stack_name, 'LINE' type_name");
		List<String> dateRange = getBetweenDate(start, end);
		for (String date : dateRange) {
			String colNmae = "xAxis_" + date;
			sql.SELECT("sum(case DATE_FORMAT(a.cdate,'%Y-%m-%d') when '" + date + "' then a.money else 0 end) '"
					+ colNmae + "'");
		}
		sql.FROM("bus_bill a").LEFT_OUTER_JOIN("bas_catalog b on a.catalog_id = b.catalog_id").WHERE("b.parent_id = 6")
				.WHERE("a.cdate >= '" + start + "'").WHERE("a.catalog_id <> 21").WHERE("a.catalog_id <> 28")
				.WHERE("a.cdate <= '" + end + "'").GROUP_BY("a.catalog_name");
		String result = sql.toString();
		log.info(result);
		return result;
	}

	public Option getHouseChartPie(String start, String end) throws Exception {
		Option op = null;
		try (Connection c = dataSource.getConnection()) {
			String sql = getHousePieSql(start, end);
			PreparedStatement state = c.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			op = EchartsOptionFactory.createPieOption("Property Fees", rs);
		}
		return op;
	}

	private String getHousePieSql(String start, String end) {
		// 按时间和类型统计
		SQL sql = new SQL().SELECT("a.catalog_name, sum(a.money) value");
		sql.FROM("bus_bill a").LEFT_OUTER_JOIN("bas_catalog b on a.catalog_id = b.catalog_id").WHERE("b.parent_id = 6")
				.WHERE("a.cdate >= '" + start + "'").WHERE("a.cdate <= '" + end + "'").GROUP_BY("a.catalog_name");
		String result = sql.toString();
		log.info(result);
		return result;
	}

	public List<String> getBetweenDate(String start, String end) {
		List<String> list = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);

		long distance = ChronoUnit.MONTHS.between(startDate, endDate);
		if (distance < 1) {
			return list;
		}
		Stream.iterate(startDate, d -> {
			return d.plusMonths(1);
		}).limit(distance + 1).forEach(f -> {
			list.add(f.toString());
		});
		return list;
	}

	public Option getBillWeekChartBar(String start, String end) throws Exception {
		Option op = null;
		try (Connection c = dataSource.getConnection()) {
			String sql = getBillWeekChartBarSql(start, end);
			PreparedStatement state = c.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			op = EchartsOptionFactory.createOption("每周消费", rs);
		}
		return op;
	}

	private String getBillWeekChartBarSql(String start, String end) {
		List<String> weekRange = billMapper.selectBillWeekRange(start, end);
		// 按时间和类型统计
		SQL sql = new SQL().SELECT("a.who catalog_name, null stack_name, 'BAR' type_name");
		for (String week : weekRange) {
			String colNmae = "xAxis_" + week;
			sql.SELECT("sum(case date_format(a.cdate, '%x%v') when '" + week + "' then a.money else 0 end) '" + colNmae
					+ "'");
		}
		sql.FROM("bus_bill a").LEFT_OUTER_JOIN("bas_catalog b on a.catalog_id = b.catalog_id")
				.WHERE("a.cdate >= '" + start + "'").WHERE("a.cdate <= '" + end + "'").GROUP_BY("a.who");
		String result = sql.toString();
		log.info(result);
		return result;
	}

	public Option getBillMonthChartBar(String start, String end) throws Exception {
		Option op = null;
		try (Connection c = dataSource.getConnection()) {
			String sql = getBillMonthChartBarSql(start, end);
			PreparedStatement state = c.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			op = EchartsOptionFactory.createOption("每月消费", rs);
		}
		return op;
	}
	
	private String getBillMonthChartBarSql(String start, String end) {
		List<String> weekRange = billMapper.selectBillMonthRange(start, end);
		// 按时间和类型统计
		SQL sql = new SQL().SELECT("a.who catalog_name, null stack_name, 'BAR' type_name");
		for (String week : weekRange) {
			String colNmae = "xAxis_" + week;
			sql.SELECT("sum(case date_format(a.cdate, '%Y%m') when '" + week + "' then a.money else 0 end) '" + colNmae
					+ "'");
		}
		sql.FROM("bus_bill a").LEFT_OUTER_JOIN("bas_catalog b on a.catalog_id = b.catalog_id")
				.WHERE("a.cdate >= '" + start + "'").WHERE("a.cdate <= '" + end + "'").GROUP_BY("a.who");
		String result = sql.toString();
		log.info(result);
		return result;
	}

}
