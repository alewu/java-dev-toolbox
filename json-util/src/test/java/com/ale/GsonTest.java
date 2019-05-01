package com.ale;


import com.ale.bean.Dept;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Map;

public class GsonTest {
    private static Gson gson = new Gson();

    @Test
    public void test(){
        String jsonStr = "{\"renter\":{\"renterId\":1,\"renterName\":\"kk\",\"renterMobile\":\"13231323552\"," +
                "\"idCard\":\"362430199610123669\",\"endTime\":\"2017-06-18\"," +
                "\"payTime\":12,\"payPeriod\":2,\"remindTime\":\"2017-03-12\"," +
                "\"remindRemark\":\"我是koko\"},\"weFee\":{\"waterPrice\":25,\"initWaterReading\":25," +
                "\"waterTonnage\":36,\"electricPrice\":29,\"initElectricReading\":36," +
                "\"electricDegree\":36},\"fixedFees\":[{\"fixedFeeId\":1,\"feePrice\":50}]}";
        Gson gson = new Gson();
        // json转换成map或者其他类型
        Map map = gson.fromJson(jsonStr, Map.class);
        System.out.println(map.get("renter"));
        System.out.println(map.get("fixedFees"));

        // javaBean转json对象
        Dept dept = new Dept(1L,"jack");
        String a = gson.toJson(dept);
        System.out.println(a);
    }

    /**
     * json字符串转基本类型数组(String虽不是基本数据类型，但是是值传递，与基本数据类型处理相似)
     */
    @Test
    public void testJsonArray() {
        String jsonArray = "[\"C\",\"Java\",\"Python\"]";
        String[] strings = gson.fromJson(jsonArray, String[].class);
        System.out.println(strings[1]);
    }

    /**
     * 使用GsonBuilder导出null值、格式化输出、日期时间
     */
    @Test
    public void testGsonBuilder(){
        Dept dept = new Dept(1L,"kook");
        System.out.println(gson.toJson(dept)); // {"deptId":1,"deptName":"kook"}

        Gson gson1 = new GsonBuilder().serializeNulls().create();
        Dept dept1 = new Dept(1L,"kook");
        System.out.println(gson1.toJson(dept1));

        Gson gson2 = new GsonBuilder()
                //序列化null
                .serializeNulls()
                // 设置日期时间格式，另有2个重载方法
                // 在序列化和反序化时均生效
                .setDateFormat("yyyy-MM-dd")
                // 禁此序列化内部类
                .disableInnerClassSerialization()
                //生成不可执行的Json（多了 )]}' 这4个字符）
                .generateNonExecutableJson()
                //禁止转义html标签
                .disableHtmlEscaping()
                //格式化输出
                .setPrettyPrinting()
                .create();
    }


}
