package com.ale.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 响应处理工具类
 *
 * @author alewu
 * @date 2020/5/23
 */
@Slf4j
public final class JsonUtil {

    public static <T> T parse(String content, TypeReference<T> valueTypeRef) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.error("ResponseUtil: parse error", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class TimestampDeserialize extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
                JsonProcessingException {
            JsonNode node = p.getCodec().readTree(p);
            Iterator<JsonNode> iterator = node.get("birthday").elements();


            return null;
        }
    }

    public static List<JSONObject> transferToJsonObjects(String json) {
        List<JSONObject> jsonObjects = Lists.newArrayList();
        Object jsonObject = JSON.parse(json);
        if (jsonObject instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) jsonObject;
            for (int k = 0; k < jsonArray.size(); k++) {
                JSONObject parameterObject = jsonArray.getJSONObject(k);
                jsonObjects.add(parameterObject);
            }
        } else if (jsonObject instanceof JSONObject) {
            JSONObject jsonObject3 = (JSONObject) jsonObject;
            jsonObjects.add(jsonObject3);
        }
        return jsonObjects;
    }

}
