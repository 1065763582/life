package com.lzq.echarts.factroy;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.lzq.echarts.AxisPointer;
import com.lzq.echarts.data.Data;
import com.lzq.echarts.Grid;
import com.lzq.echarts.Legend;
import com.lzq.echarts.Option;
import com.lzq.echarts.Title;
import com.lzq.echarts.Tooltip;
import com.lzq.echarts.axis.CategoryAxis;
import com.lzq.echarts.axis.ValueAxis;
import com.lzq.echarts.code.Orient;
import com.lzq.echarts.code.PointerType;
import com.lzq.echarts.code.Trigger;
import com.lzq.echarts.series.Bar;
import com.lzq.echarts.series.Line;
import com.lzq.echarts.series.Pie;
import com.lzq.echarts.series.Series;

/**
 * 
 * @Title EchartsFactory.java
 * @Package com.lzq.echarts
 * @Description TODO(用一句话描述该文件做什么)
 * @author lizhiqiang
 * @date May 21, 2020 9:07:39 PM
 * 
 */
public class EchartsOptionFactory {
	/**
	 * 
	 * @Title createOption 创建折线图和柱状图
	 * @Description java.sql.ResultSet 转为Echart.Option <br/>
	 *              列为X轴, 列名以 'xAxis_' 开头, <br/>
	 *              列'catalog_name'数据作为分类legend,<br/>
	 *              列'type_name'数据作为图标类型type,<br/>
	 *              列'stack_name'数据作为数据堆叠stack
	 * @param <T> 数据集的数据类型
	 * @param rs  数据集
	 * @return com.lzq.echarts.Option
	 * @throws Exception
	 *
	 */
	public static <T> Option createOption(String titleText, ResultSet rs) throws Exception {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		List<String> xAxisName = new ArrayList<>();
		// 获取X轴数据
		for (int i = 0; i < columnCount; i++) {
			String column = rsmd.getColumnName(i + 1);
			if (column.indexOf("xAxis_") == 0) {
				xAxisName.add(column);
			}
		}
		// 获取series数据
		List<Series<?>> seriesList = new ArrayList<>();
		Set<String> legends = new LinkedHashSet<>();
		while (rs.next()) {
			String catalogName = rs.getString("catalog_name");
			String stackName = rs.getString("stack_name");
			String typeName = rs.getString("type_name");
			List<BigDecimal> data = xAxisName.stream().map(col -> {
				try {
					return rs.getBigDecimal(col);
				} catch (SQLException e) {
					return BigDecimal.ZERO;
				}
			}).collect(Collectors.toList());
			// 图例的类型
			legends.add(catalogName);
			if ("BAR".equals(typeName)) {
				Bar bar = new Bar(catalogName);
				bar.stack(stackName);
				bar.data(data);
				seriesList.add(bar);
			} else if ("LINE".equals(typeName)) {
				Line line = new Line(catalogName);
				line.stack(stackName);
				line.data(data);
				seriesList.add(line);
			}

		}
		List<String> fixXAxisName = xAxisName.stream().map(str -> str.substring(6)).collect(Collectors.toList());
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(Trigger.axis);
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.setType(PointerType.shadow);
		tooltip.axisPointer(axisPointer);
		Option option = new Option().title(titleText).legend(new Legend(legends))
				.grid(new Grid().left("3%").right("4%").bottom("3%").containLabel(true)).tooltip(tooltip)
				.xAxis(new CategoryAxis().data(fixXAxisName)).yAxis(new ValueAxis()).series(seriesList);
		return option;
	}

	public static <T> Option createPieOption(String titleText, ResultSet rs) throws Exception {
		Set<String> legends = new LinkedHashSet<>();
		BigDecimal total = new BigDecimal(0);
		Pie pie = new Pie(titleText).radius("65%");
		while (rs.next()) {
			String key = rs.getString("catalog_name");
			BigDecimal value = rs.getBigDecimal("value");
			// 图例的类型
			legends.add(key);
			Data data = new Data(key, value);
			pie.data().add(data);
			total = total.add(value);
		}
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(Trigger.item);
		tooltip.formatter("{a} <br/>{b} : {c} ({d}%)");
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.setType(PointerType.shadow);
		tooltip.axisPointer(axisPointer);
		Option option = new Option().title(new Title().text(total.toString()).subtext(titleText).left("center"))
				.legend(new Legend(legends).orient(Orient.vertical).left("left"))
				.grid(new Grid().left("3%").right("4%").bottom("3%").containLabel(true)).tooltip(tooltip).series(pie);
		return option;
	}
}
