package com.ale;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JsonPathTest {

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
    void testJsonPath() throws JsonProcessingException {
        //使用jackson对字符串反序列化为json对象
        ObjectMapper mapper = new ObjectMapper();
        HashMap responseJson = mapper.readValue(json, HashMap.class);
        // 输出text的值
        String text = JsonPath.read(responseJson, "$.store.bicycle.color");
        System.out.println(text);
    }

    @Test
    void testGetSingleValue(){
        //获取单个值
        long start = System.currentTimeMillis();
        String roleId1 = JsonPath.read(json, "$.store.bicycle.color");
        Integer price1 = JsonPath.read(json, "$.store.bicycle.price");
        System.out.println("color:" + roleId1);
        System.out.println("price:" + price1);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    void testGetAll(){
        //获取所有值
        List<Object> roleIds = JsonPath.read(json, "$.store.book[*]");
        System.out.println("book:" + roleIds);

        //可以提前编辑一个路径，并多次使用它
        JsonPath path = JsonPath.compile("$.store.book[*]");
        List<Object> rvNoRecords3 = path.read(json);
        System.out.println (rvNoRecords3 );
    }

    @Test
    void test(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            List<Object> roleIds = JsonPath.read(json, "$.store.book[*]");
//            System.out.println("book:" + roleIds);
        }
        System.out.println(" ss " + (System.currentTimeMillis() - start));

    }
    @Test
    void test11(){
        long start = System.currentTimeMillis();
        JsonPath path = JsonPath.compile("$.store.book[*]");
        for (int i = 0; i < 100000; i++) {
            List<Object> rvNoRecords3 = path.read(json);
        }
        System.out.println(" 1ss " + (System.currentTimeMillis() - start));

    }

    @Test
    void test12(){
        ReadContext ctx = JsonPath.parse(json);
        List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");
        System.out.println(authorsOfBooksWithISBN);
    }

    @Test
    void test22(){
        String json = FileUtil.readUtf8String(new File("D:\\docs\\ops\\mnf_banks.json"));
        List<Map<String, String>> mnf_banks = JsonPath.read(json, "$.responseBody[*]");

        ArrayList<String> mnf = Lists.newArrayList();
        for (Map<String, String> mnf_bank : mnf_banks) {
            String code = mnf_bank.get("code");
            mnf.add(code);
//            System.out.println(code);
        }


        String json2 = FileUtil.readUtf8String(new File("D:\\docs\\ops\\paystack bank list.json"));
        List<Map<String, String>> paystack = JsonPath.read(json2, "$.data[*]");

        ArrayList<String> common = Lists.newArrayList();

        for (Map<String, String> ps : paystack) {
            String code = ps.get("code");
            if (mnf.contains(code)) {
                common.add("common: " + code);
            }
//            System.out.println(code);
        }
        System.out.println(mnf_banks);


        for (String s : common) {
            System.out.println(s);
        }

        System.out.println(common.size());


    }

    @Test
    void testJsonPathUtil(){
        String resp = "{\n" +
                "    \"code\": \"200\",\n" +
                "    \"message\": \"ok\",\n" +
                "    \"data\": {\n" +
                "        \"referenceId\": \"123241313123123123\",\n" +
                "        \"status\": \"SUCCEED\",\n" +
                "        \"message\": \"SSSSSSSSSA\"\n" +
                "    }\n" +
                "}";
        JSONObject entries = JSONUtil.parseObj(resp);
        Object byPath = entries.getByPath("data.status");
        Object byPath1 = entries.getByPath("code");
        System.out.println(byPath);
        System.out.println(byPath1);
    }


}
