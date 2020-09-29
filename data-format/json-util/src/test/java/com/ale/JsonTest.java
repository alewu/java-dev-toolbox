package com.ale;

import com.ale.bean.CustomTag;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

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
}
