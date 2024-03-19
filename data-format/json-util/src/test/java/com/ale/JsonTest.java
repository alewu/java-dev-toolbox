package com.ale;

import com.ale.bean.CustomTag;
import com.ale.util.JsonUtils;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonTest {

    @Test
    void test(){
        String json = "{\n" +
                "    \"appid\": \"demoData\",\n" +
                "    \"activeStatus\": 1,\n" +
                "    \"gender\": 1,\n" +
                "    \"isSubscribe\": 1,\n" +
                "    \"customTag\": {\n" +
                "        \"payStatus\": \"12\",\n" +
                "        \"isPurchased\": \"\"\n" +
                "    }\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(json);
        String string = jsonObject.getString("customTag");
        CustomTag customTag = JSON.parseObject(string, CustomTag.class);
        System.out.println(Objects.isNull(customTag));

        Map<String, Object> objectMap = JsonUtils.toMap(json);
        Object tag = objectMap.get("customTag");
        Map<String, Object> toMap = JsonUtils.toMap(tag);
        System.out.println(toMap.get("payStatus"));
    }

    @Test
    void test1(){
        String json = "{\"type\":2,\"frequentlyLink\":\"1\"}";
//        List<JSONObject> jsonObjects = JSON.parseArray(json, JSONObject.class);

        JSONObject jsonObject = JsonUtils.parseObj(json, JSONObject.class);
        Map<String, Object> map = JsonUtils.toMap(json);
        int type = JsonUtils.getIntValue(map, "frequentlyLink");
        System.out.println(type);
        System.out.println(jsonObject);
    }

    @Test
    void test2(){
        String json = "";
        JSONObject jsonObject = JSON.parseObject(json);
        Object responseBody = jsonObject.get("responseBody");
        JSONArray jsonArray = JSON.parseArray(responseBody.toString());

        JsonPath path = JsonPath.compile("$.responseBody[*]");
        List<LinkedHashMap<String, Object>> rvNoRecords3 = path.read(json);
        List<LinkedHashMap<String, Object>> ussdTemplate = rvNoRecords3.stream().filter(x -> Objects.nonNull(x.get(
                "ussdTemplate"))).collect(Collectors.toList());
        for (LinkedHashMap<String, Object> o : ussdTemplate) {
            if (Objects.nonNull(o.get("ussdTemplate"))) {
                System.out.println(o);
            }

        }
        System.out.println(ussdTemplate.size());
    }
}
