package com.ale;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;

/**
 * @author Administrator
 * @date 2024/3/16 18:29
 */
public class GodaddyRestfulUtil {

    public static final String BASE_URL = "https://api.godaddy.com";

    public static final String TOKEN = "";

    public static String listDomains() {
        HttpRequest get = HttpUtil.createGet(BASE_URL + "/v1/domains");
        return get
                .setHttpProxy("127.0.0.1", 4780)
                .auth(TOKEN)
                .execute()
                .body();
    }

    public static String available(String domain) {
        HttpRequest get = HttpUtil.createGet(BASE_URL + "/v1/domains/available" +"?domain="+ domain);
        return get
                .setHttpProxy("127.0.0.1", 4780)
                .auth(TOKEN)
                .execute()
                .body();
    }

    public static String suggest(String domain) {
        HttpRequest get = HttpUtil.createGet(BASE_URL + "/v1/domains/suggest" +"?query="+ domain);
        return get
                .setHttpProxy("127.0.0.1", 4780)
                .auth(TOKEN)
                .execute()
                .body();
    }

    public static String getDomainDetail(String domain) {
        HttpRequest get = HttpUtil.createGet(StrUtil.format(BASE_URL + "/v1/domains/{}", domain));
        return get
                .setHttpProxy("127.0.0.1", 4780)
                .auth(TOKEN)
                .execute()
                .body();
    }

    public static String getDNSRecord(String domain) {
        HttpRequest get = HttpUtil.createGet(StrUtil.format(BASE_URL + "/v1/domains/{}/records", domain));
        return get
                .setHttpProxy("127.0.0.1", 4780)
                .auth(TOKEN)
                .timeout(30 * 1000)
                .execute()
                .body();

    }

    public static void addDNSRecords(String domain) {
        HttpRequest post = HttpUtil.createRequest(Method.PATCH, StrUtil.format(BASE_URL + "/v1/domains/{}/records", domain));
        String body = post
                .setHttpProxy("127.0.0.1", 4780)
                .auth(TOKEN)
                .contentType("application/json")
                .body("[" +
                        "    {\n" +
                        "  \"data\": \"8.211.34.49\",\n" +
                        "  \"name\": \"admin\",\n" +
                        "  \"type\": \"A\",\n" +
                        "  \"ttl\": 600\n" +
                        "}" +
                        "]")
                .timeout(30 * 1000)
                .execute()
                .body();
        System.out.println(body);
    }
}
