package com.ale;

import com.ale.bean.CustomTag;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
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
                "        \"payStatus\": \"\",\n" +
                "        \"isPurchased\": \"\"\n" +
                "    }\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(json);
        String string = jsonObject.getString("customTag");
        CustomTag customTag = JSON.parseObject(string, CustomTag.class);
        System.out.println(Objects.isNull(customTag));
    }

    @Test
    void test1(){
        String json = "{\"type\":2,\"frequentlyLink\":\"1\"}";
        List<JSONObject> jsonObjects = JSON.parseArray(json, JSONObject.class);
        System.out.println(json);
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
