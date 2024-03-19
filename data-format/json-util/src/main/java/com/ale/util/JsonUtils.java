package com.ale.util;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author wywuj
 */
public class JsonUtils {

    /**
     * Json处理代理对象
     */
    private static final JsonDelegate JSON_DELEGATE;

    static {
        JsonDelegate delegateToUse = null;
        // com.fasterxml.jackson.databind.ObjectMapper?
        if (JsonUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", JsonUtils.class.getClassLoader())) {
            delegateToUse = new JacksonDelegate();
        }
        // com.alibaba.fastjson.JSONObject?
        else if (JsonUtils.isPresent("com.alibaba.fastjson.JSONObject", JsonUtils.class.getClassLoader())) {
            delegateToUse = new FastJsonDelegate();
        } else if (JsonUtils.isPresent("JSONObject", JsonUtils.class.getClassLoader())) {
            delegateToUse = new JacksonDelegate();
        }
        JSON_DELEGATE = delegateToUse;
    }


    /**
     * 将 Object 转为json字符串
     *
     * @param object object
     * @return JsonString
     */
    public static String toJsonStr(Object object) {
        if (JSON_DELEGATE == null) {
            throw new RuntimeException("Jackson, Fastjson and JsonKit are not supported");
        }
        return JSON_DELEGATE.toJsonStr(object);
    }

    /**
     * 将 json字符串 转为Object
     *
     * @param jsonString
     * @param clazz
     * @return T
     */
    public static <T> T parseObj(String jsonString, Class<T> clazz) {
        if (JSON_DELEGATE == null) {
            throw new RuntimeException("Jackson, Fastjson and JsonKit are not supported");
        }
        return JSON_DELEGATE.parseObj(jsonString, clazz);
    }

    /**
     * 确定class是否可以被加载
     *
     * @param className
     * @param classLoader
     * @return
     */
    private static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            Class.forName(className, true, classLoader);
            return true;
        } catch (Throwable ex) {
            // Class or one of its dependencies is not present...
            return false;
        }
    }

    /**
     * Json 委托，默认使用 默认使用jackson 再次fastJson 最后使用jsonKit
     */
    private interface JsonDelegate {
        /**
         * 对象转json
         *
         * @param object object
         * @return jsonStr
         */
        String toJsonStr(Object object);

        /**
         * json转对象
         *
         * @param jsonStr jsonStr
         * @param clazz   clazz
         * @param <T>     clazz
         * @return clazz
         */
        <T> T parseObj(String jsonStr, Class<T> clazz);
    }

    /**
     * jackson委托
     */
    private static class JacksonDelegate implements JsonDelegate {

        static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

        static ObjectMapper createObjectMapper() {
            final ObjectMapper mapper = new ObjectMapper();
            //        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
            mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            // disabled features:
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            return mapper;
        }

        @Override
        public String toJsonStr(Object object) {
            try {
                return OBJECT_MAPPER.writeValueAsString(object);
            } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public <T> T parseObj(String jsonStr, Class<T> clazz) {
            ObjectMapper objectMapper =
                    new ObjectMapper();
            try {
                return objectMapper.readValue(jsonStr, clazz);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * fastJson委托
     */
    private static class FastJsonDelegate implements JsonDelegate {

        @Override
        public String toJsonStr(Object object) {
            return JSONObject.toJSONString(object);
        }

        @Override
        public <T> T parseObj(String jsonString, Class<T> clazz) {
            return JSON.parseObject(jsonString, clazz);
        }

    }

    public static Map<String, Object> toMap(String value) {
        return StringUtils.isNotBlank(value) ? toMap(value, () -> null) : null;
    }

    public static Map<String, Object> toMap(Object value) {
        return value != null ? toMap(value, () -> null) : null;
    }

    public static Map<String, Object> toMap(Object value, Supplier<Map<String, Object>> defaultSupplier) {
        if (value == null) {
            return defaultSupplier.get();
        }
        try {
            if (value instanceof Map) {
                return (Map<String, Object>) value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toMap(toJsonStr(value), defaultSupplier);
    }

    public static Map<String, Object> toMap(String value, Supplier<Map<String, Object>> defaultSupplier) {
        if (StringUtils.isBlank(value)) {
            return defaultSupplier.get();
        }
        try {
            return parseObj(value, LinkedHashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultSupplier.get();
    }

    public static int getIntValue(Map<String, Object> map, String key) {
        if (Objects.isNull(map) || map.isEmpty()) {
            return 0;
        }
        String valueStr = String.valueOf(map.get(key));
        if (StringUtils.isBlank(valueStr) || !StringUtils.isNumeric(valueStr)) {
            return 0;
        }
        return Integer.parseInt(valueStr);
    }


}
