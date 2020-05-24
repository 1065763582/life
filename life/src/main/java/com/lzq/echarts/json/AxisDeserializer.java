

package com.lzq.echarts.json;

import com.lzq.echarts.axis.Axis;
import com.lzq.echarts.axis.CategoryAxis;
import com.lzq.echarts.axis.TimeAxis;
import com.lzq.echarts.axis.ValueAxis;
import com.lzq.echarts.code.AxisType;

import java.lang.reflect.Type;

/**
 * @author lizhiqiang
 */
public class AxisDeserializer  {
//    /**
//     * 设置json,typeOfT,context值
//     *
//     * @param json
//     * @param typeOfT
//     * @param context
//     */
//    @Override
//    public Axis deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        final JsonObject jsonObject = json.getAsJsonObject();
//        String _type = jsonObject.get("type").getAsString();
//        AxisType type = AxisType.valueOf(_type);
//        Axis axis = null;
//        switch (type) {
//            case category:
//                axis = context.deserialize(jsonObject, CategoryAxis.class);
//                break;
//            case value:
//                axis = context.deserialize(jsonObject, ValueAxis.class);
//                break;
//            case time:
//                axis = context.deserialize(jsonObject, TimeAxis.class);
//                break;
//        }
//        return axis;
//    }
}
