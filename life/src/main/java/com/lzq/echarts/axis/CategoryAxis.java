

package com.lzq.echarts.axis;

import com.lzq.echarts.code.AxisType;

/**
 * 类目轴
 *
 * @author lizhiqiang
 */
public class CategoryAxis extends Axis<CategoryAxis> {

    /**
     * 构造函数
     */
    public CategoryAxis() {
        this.type(AxisType.category);
    }

}
