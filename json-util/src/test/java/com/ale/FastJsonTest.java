package com.ale;


import com.ale.bean.Fee;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;


import java.util.List;

public class FastJsonTest {

    @Test
    public void testJsonStrToJavaBean()  {
        // 注意如果jsonStr中没有的javaBean中的字段则为空
        String feeJson = "[{\"renterId\":\"445456421234123\",\"price\":6.00,\"initNum\":5.00,\"floorNum\":16.00}," +
                "{\"renterId\":\"432514246214311423\",\"feeType\":\"电费\",\"price\":1.30,\"initNum\":4.00,\"floorNum\":45.00}]";
        List<Fee> fees = JSON.parseArray(feeJson, Fee.class);
        for (Fee f : fees) {
            System.out.println(f.toString());
        }


        String idsJson = "[445456421234123,445456421234123,445456421234123]";
        List<Long> longList = JSON.parseArray(idsJson, Long.class);
        for (Long aLong : longList) {
            System.out.println(aLong);
        }
    }
    @Test
    public void testComplexJsonStr(){
        // fastJson解析复杂的json字符串
        String jsonStr = "{\"name\":\"BeJson\",\"url\":\"http://www.bejson.com\",\"page\":88,\"isNonProfit\":true," +
                "\"address\":{\"street\":\"科技园路.\",\"city\":\"江苏苏州\",\"country\":\"中国\"}," +
                "\"links\":[{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"}]}";
        // 1、解析为json对象
        JSONObject object = JSON.parseObject(jsonStr);
        // 2、根据key来获取目标对象
        String name = object.getString("name");
        System.out.println(name);
        // 获取数组
        JSONArray jsonArray = object.getJSONArray("links");
        for (Object o : jsonArray) {
            System.out.println(o);
        }

    }

}
