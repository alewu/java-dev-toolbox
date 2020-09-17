package com.ale;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class JsonArrayTest {
    @Test
    void test(){
        String array = "[{\"type\": 0, \"index\": \"link1596607304747\", \"novelName\": \"镇国战神\", \"chapterIdx\": 1, " +
                "\"channelName\": \"掌中云\", \"expatriateOa\": \"幻海书舍\", \"accountPlatform\": \"3\"}, {\"type\": 0, " +
                "\"index\": \"link1596607325851\", \"novelName\": \"医武高手\", \"chapterIdx\": 1, \"channelName\": " +
                "\"掌中云\", \"expatriateOa\": \"幻海书舍\", \"accountPlatform\": \"3\"}, {\"type\": 0, \"index\": " +
                "\"link1596607340731\", \"novelName\": \"暗黑神尊\", \"chapterIdx\": 1, \"channelName\": \"掌中云\", " +
                "\"expatriateOa\": \"幻海书舍\", \"accountPlatform\": \"3\"}, {\"type\": 0, \"index\": " +
                "\"link1596607357363\", \"novelName\": \"大叔娇宠傲娇妻\", \"chapterIdx\": 1, \"channelName\": \"掌中云\", " +
                "\"expatriateOa\": \"幻海书舍\", \"accountPlatform\": \"3\"}, {\"type\": 0, \"index\": " +
                "\"link1596607373411\", \"novelName\": \"至尊豪雄\", \"chapterIdx\": 1, \"channelName\": \"掌中云\", " +
                "\"expatriateOa\": \"幻海书舍\", \"accountPlatform\": \"3\"}]";
        List<JSONObject> jsonObjects = JSON.parseArray(array, JSONObject.class);
        Assertions.assertEquals(5, jsonObjects.size());
        for (JSONObject jsonObject : jsonObjects) {
            System.out.println(jsonObject.get("index"));
        }

        String a = "[{\"appId\": \"wxe898b8e80c198a95\", \"appName\": \"混沌书文\", \"setGrey\": null, " +
                "\"accountPlatform\": \"2\"}]";
        System.out.println(13&17);

        System.out.println("o_cxDuDTXMkDXbNj7FWXNyVmB0lo".length());
    }
}
