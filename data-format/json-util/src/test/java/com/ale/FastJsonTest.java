package com.ale;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReUtil;
import com.ale.bean.CareerScoreInfo;
import com.ale.bean.CreditResponse;
import com.ale.bean.Fee;
import com.ale.bean.UserDeviceInfo;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class FastJsonTest {

    @Test
    void test(){
        String json = FileUtil.readString("C:\\Users\\wywuj\\Desktop\\addressBook_20240626_07063073226.json", CharsetUtil.UTF_8);
        System.out.println(JSON.parseArray(json));
    }

    @Test
    void testJsonStrToJavaBean() {
        // 注意如果jsonStr中没有的javaBean中的字段则为空
        String feeJson = "[{\"renterId\":\"445456421234123\",\"price\":6.00,\"initNum\":5.00,\"floorNum\":16.00}," +
                "{\"renterId\":\"432514246214311423\",\"feeType\":\"电费\",\"price\":1.30,\"initNum\":4.00," +
                "\"floorNum\":45.00}]";
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
    void testJavaBeanToJsonStr() {

        UserDeviceInfo userDeviceInfo = new UserDeviceInfo();
        userDeviceInfo.setAvailableStorage("2.3G");
        userDeviceInfo.setIsRoot("1");
        userDeviceInfo.setIsWifi("0");

        JSONObject o = (JSONObject) JSON.toJSON(userDeviceInfo);
        String isRoot = o.getString("isRoot");
        System.out.println(isRoot);

        String s = JSONObject.toJSONString(userDeviceInfo);
        System.out.println(s);
    }

    @Test
    void testComplexJsonStr() {
        // fastJson解析复杂的json字符串
        String jsonStr = "{\"name\":\"BeJson\",\"url\":\"http://www.bejson.com\",\"page\":88,\"isNonProfit\":true," +
                "\"address\":{\"street\":\"科技园路.\",\"city\":\"江苏苏州\",\"country\":\"中国\"}," +
                "\"links\":[{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\"," +
                "\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"}]}";
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

    @Test
    void test111() {
        String news = "{\"articles\":[{\"description\":\"啊手动阀\",\"title\":\"安抚a\",\"url\":\"http://sp.upuptec" +
                ".cn/h5/index.html?linkId=1185\"}]}";
        String s = ReUtil.replaceAll(news, "linkId=\\d+", "$0&uid=12");
        System.out.println(s);
    }

    @Test
    void testJsonToMap() {
        String a = "{\n" +
                "    \"text\": {\n" +
                "        \"content\": \"啊啊<a href='http://sp.upuptec.cn/h5/index.html?linkId=1185'>请求</a>\"\n" +
                "    },\n" +
                "    \"msgtype\": \"text\"\n" +
                "}";

        Map map = JSONObject.parseObject(a, Map.class);
        //  Map<String, Object> map = JSONObject.parseObject(a);
        map.put("touser", "e.getOpenid()");
        map.put("appid", "aa");
        map.put("nickName", "e.getNickname()");

        Map contentMap = (Map) map.get("text");
        String content1 = MapUtil.getStr(contentMap, "contentMap");
        String newContent = ReUtil.replaceAll(content1, "linkId=\\d+", "$0&uid=".concat(String.valueOf(11)));
        contentMap.put("contentMap", newContent);


        String s1 = JSON.toJSONString(map);
        System.out.println(s1);
    }

    @Test
    void testJsonToMap1() {
        String json = "{\n" +
                "    \"text\": {\n" +
                "        \"content\": \"啊啊<a href='http://sp.upuptec.cn/h5/index.html?linkId=1185'>请求</a>\"\n" +
                "    },\n" +
                "    \"msgtype\": \"text\"\n" +
                "}";

        Map<String, Object> map = JSONObject.parseObject(json);
        map.put("touser", "xxx");
        map.put("appid", "aa");
        map.put("nickName", "xxx");

        JSONObject contentJSONObject = (JSONObject) map.get("text");
        String content = contentJSONObject.getString("content");
        String newContent = ReUtil.replaceAll(content, "linkId=\\d+", "$0&uid=".concat(String.valueOf(11)));
        contentJSONObject.put("content", newContent);


        String s1 = JSON.toJSONString(map);
        System.out.println(s1);
    }

    @Test
    void testMapToJsonObject() {
        String json = "{\n" +
                "    \"text\": {\n" +
                "        \"content\": \"啊啊<a href='http://sp.upuptec.cn/h5/index.html?linkId=1185'>请求</a>\"\n" +
                "    },\n" +
                "    \"msgtype\": \"text\"\n" +
                "}";

        Map<String, Object> map = JSONObject.parseObject(json);

        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        String text = jsonObject.getString("text");
        String newContent = ReUtil.replaceAll(text, "linkId=\\d+", "$0&uid=".concat(String.valueOf(11)));

        jsonObject.put("text", newContent);
        String s1 = JSON.toJSONString(map);
        System.out.println(s1);
    }


    @Test
    void testNestJson() {
        String json = "{\"update_time\":\"2021-10-31 09:41:38\",\"status\":1,\"error\":\"查询成功\"," +
                "\"data\":{\"score\":\"451\",\"name\":\"张三\",\"address\":\"北京市 " +
                "朝阳区,\",\"phoneMatched\":true}}";
        CreditResponse<CareerScoreInfo> creditResponse = JSON.parseObject(json,
                                                                          new TypeReference<CreditResponse<CareerScoreInfo>>() {
                                                                          });
        System.out.println(creditResponse.getData());
    }

    @Test
    void testNestFastJson() {
        String json = "{\"update_time\":\"2021-10-31 09:41:38\",\"status\":1,\"error\":\"查询成功\"," +
                "\"data\":{\"score\":\"451\",\"name\":\"张三\",\"address\":\"北京市 " +
                "朝阳区,\",\"phoneMatched\":true}}";
        CreditResponse<CareerScoreInfo> creditResponse = JSON.parseObject(json,
                new TypeReference<CreditResponse<CareerScoreInfo>>() {
                });
        System.out.println(creditResponse.getData());

        String s = com.alibaba.fastjson2.JSON.toJSONString(creditResponse);
        System.out.println(s);
    }
}
