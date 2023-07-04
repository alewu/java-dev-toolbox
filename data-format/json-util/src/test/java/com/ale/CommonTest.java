package com.ale;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

class CommonTest {

    public static final String json = "{" +
            "  \"store\": {" +
            "    \"book\": [{" +
            "      \"title\": \"Java虚拟机\"," +
            "      \"price\": 20" +
            "    }, {" +
            "      \"title\": \"Head First 设计模式\"," +
            "      \"price\": 72" +
            "    }, {" +
            "      \"title\": \"重构：改善既有代码的设计\"," +
            "      \"isbn\": \"123\"," +
            "      \"price\": 38" +
            "    }, {" +
            "      \"title\": \"Spring实践4\"," +
            "      \"isbn\": \"321\"," +
            "      \"price\": 32" +
            "    }, {" +
            "      \"title\": \"图解HTTP\"," +
            "      \"isbn\": \"543\"," +
            "      \"price\": 25" +
            "    }]," +
            "    \"bicycle\": {" +
            "      \"color\": \"red\"," +
            "      \"price\": 219" +
            "    }" +
            "  }" +
            "}";

    @Test
    void testOrgJson(){
        Map<String, String> abc = new HashMap<>();
        abc.put("b", "2");
        abc.put("c", "3");
        abc.put("a", "1");
        org.json.JSONObject jsonObject = new org.json.JSONObject(abc);
        SortedMap map = new TreeMap(jsonObject.toMap());
        org.json.JSONObject a = new org.json.JSONObject(map);
        System.out.println(a);

        org.json.JSONObject json = new org.json.JSONObject();
        json.put("accessKey", "accessKey");
        json.put("secretKey", "secretKey");
        json.put("from", "from");
        System.out.println(json.toString());
    }

    @Test
    void test(){
        String json = "{\n" +
                "  \"isToAll\": false,\n" +
                "  \"sex\": \"0\",\n" +
                "  \"followDays\": 17,\n" +
                "  \"lastActiveTime\": \"2020-07-27 14:45:52\"\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(json);
        String isToAll = jsonObject.getString("isToAll");
        Boolean isToAll1 = jsonObject.getBoolean("isToAll");
        System.out.println();
        System.out.println(isToAll);
        System.out.println(isToAll1);
    }

    @Test
    void test1(){
        CompletableFuture<Boolean> future = CompletableFuture.completedFuture(Boolean.TRUE);
        System.out.println(Objects.equals(future.join(), Boolean.TRUE));

        cn.hutool.json.JSONObject entries = JSONUtil.createObj().set("", "");
        entries.getByPath("", String.class);
    }
}
