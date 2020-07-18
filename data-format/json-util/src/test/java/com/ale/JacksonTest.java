package com.ale;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.*;


public class JacksonTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        try {
            String jsonStr = "{\"renter\":{\"renterId\":1,\"renterName\":\"kk\",\"renterMobile\":\"13231323552\"," +
                    "\"wechatId\":\"1101747855\",\"idCard\":\"362430199610123669\"," +
                    "\"renterImage\":\"http: //www.baidu.com\",\"emergencyContact\":\"王\"," +
                    "\"emergencyMobile\":\"13213123552\",\"startTime\":\"2017-12-12\",\"endTime\":\"2017-06-18\"," +
                    "\"payTime\":12,\"payPeriod\":2,\"renterNum\":5,\"remindDay\":5,\"remindTime\":\"2017-03-12\"," +
                    "\"remindRemark\":\"我是koko\"},\"weFee\":{\"waterPrice\":25,\"initWaterReading\":25," +
                    "\"waterTonnage\":36,\"electricPrice\":29,\"initElectricReading\":36," +
                    "\"electricDegree\":36},\"fixedFees\":[{\"fixedFeeId\":1,\"feePrice\":50}]}";

            JsonNode jsonNode = MAPPER.readTree(jsonStr);
            System.out.println(jsonNode.get("renter"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJackson() throws IOException {
        Map map = new HashMap();
        List list = new ArrayList();
        Set set = new HashSet();
        Date date = new Date();

        String jsonString = MAPPER.writeValueAsString("");
        System.out.println(jsonString);
    }

    @Test
    public void testJsonStrToMap() throws IOException {

        Map map = new HashMap();

        map.put("a", null);

        map.put("b", "b");

        String ret_val = MAPPER.writeValueAsString(map);

        System.out.println(ret_val);

        Map m = MAPPER.readValue(ret_val, Map.class);

        System.err.println(m.get("a") + "|" + m.get("b"));
    }

    @Test
    public void testJsonStrToJavaBean() {
        //JSON字符串转javaBean
        //JavaBean javaBean= MAPPER.readValue(jsonString, JavaBean.class);
    }

    @Test
    public void testJavaBeanToJsonStr() {
        //javaBean转JSON字符串
        //String jsonString = MAPPER.writeValueAsString(javaBean);


    }

    @Test
    public void testJsonNode() throws IOException {
        // 读取jsonString，将整个jsonString作为根节点
        //JsonNode jsonNode = MAPPER.readTree(jsonString);
        String jsonString = "{\"rent\":\"5556\",\"data\":[{\"1层\":[\"A101\",\"A102\",\"A103\",\"A104\",\"A105\"," +
                "\"A106\"]},{\"2层\":[\"A201\",\"A202\",\"A203\",\"A204\",\"A205\",\"A206\"]},{\"3层\":[\"A301\"," +
                "\"A302\",\"A303\",\"A304\",\"A305\",\"A306\"]},{\"4层\":[\"A401\",\"A402\",\"A403\",\"A404\"," +
                "\"A405\",\"A406\"]},{\"5层\":[\"A501\",\"A502\",\"A503\",\"A504\",\"A505\",\"A506\"]}]}";
        JsonNode jsonNode = MAPPER.readTree(jsonString);
        JsonNode rentNode = jsonNode.get("rent");
        JsonNode dataNode = jsonNode.get("data");
        System.out.println(rentNode.toString());
        System.out.println(dataNode.toString());
    }


}
