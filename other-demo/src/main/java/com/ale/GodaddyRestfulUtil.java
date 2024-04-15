package com.ale;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * https://developer.godaddy.com/doc/endpoint/domains#/
 * The type Godaddy restful util.
 *
 * @author Administrator
 * @date 2024 /3/16 18:29
 */
public class GodaddyRestfulUtil {

    /**
     * The constant BASE_URL.
     */
    public static final String BASE_URL = "https://api.godaddy.com";

    /**
     * The constant HOST.
     */
    public static final String HOST = "127.0.0.1";

    /**
     * The constant PORT.
     */
    public static final int PORT = 4780;

    /**
     * The constant AUTHORIZATION_TOKEN.
     */
    public static final String AUTHORIZATION_TOKEN = "sso-key " + System.getenv("GODADDY_API_KEY") + ":" + System.getenv("GODADDY_API_SECRET");

    /**
     * List domains string.
     *
     * @return the string
     */
    public static String listDomains() {
        HttpRequest get = HttpUtil.createGet(BASE_URL + "/v1/domains");
        return get
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .execute()
                .body();
    }

    /**
     * Available string.
     *
     * @param domain the domain
     * @return the string
     */
    public static String available(String domain) {
        HttpRequest get = HttpUtil.createGet(BASE_URL + "/v1/domains/available" + "?domain=" + domain);
        return get
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .execute()
                .body();
    }

    /**
     * Suggest string.
     *
     * @param domain the domain
     * @return the string
     */
    public static String suggest(String domain) {
        HttpRequest get = HttpUtil.createGet(BASE_URL + "/v1/domains/suggest" + "?query=" + domain);
        return get
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .execute()
                .body();
    }

    /**
     * Gets domain detail.
     *
     * @param domain the domain
     * @return the domain detail
     */
    public static String getDomainDetail(String domain) {
        HttpRequest get = HttpUtil.createGet(StrUtil.format(BASE_URL + "/v1/domains/{}", domain));
        return get
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .execute()
                .body();
    }

    /**
     * Replace all DNS Records for the specified Domain
     *
     * @param domain the domain
     * @return the dns record
     */
    public static String getAllDNSRecord(String domain) {
        HttpRequest get = HttpUtil.createGet(StrUtil.format(BASE_URL + "/v1/domains/{}/records", domain));
        return get
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .timeout(60 * 1000)
                .execute()
                .body();

    }

    /**
     * Add the specified DNS Records to the specified Domain
     *
     * @param domain     the domain
     * @param dnsRecords the dns records
     */
    public static void addDNSRecords(String domain, List<DNSRecord> dnsRecords) {
        HttpRequest post = HttpUtil.createRequest(Method.PATCH, StrUtil.format(BASE_URL + "/v1/domains/{}/records", domain));
        String body = post
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .contentType("application/json")
                .body(JSONUtil.toJsonStr(dnsRecords))
                .timeout(30 * 1000)
                .execute()
                .body();
        System.out.println(body);
    }

    /**
     * Retrieve DNS Records for the specified Domain,optionally with the specified Type and/or Name
     *  @param domain     the domain
     * @param type       the type
     */
    public static String retrieveDNSRecordsOptionallyWithTheSpecifiedType(String domain, String type, String name) {
        HttpRequest post = HttpUtil.createRequest(Method.GET, StrUtil.format(BASE_URL + "/v1/domains/{}/records/{}/{}", domain, type, name));
        return post
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .contentType("application/json")
                .timeout(30 * 1000)
                .execute()
                .body();

    }

    /**
     * Replace all DNS Records for the specified Domain with the specified Type and Name
     *
     * @param domain     the domain
     * @param type       the type
     * @param dnsRecords the dns records
     */
    public static void replaceAllDNSRecordsWithTheSpecifiedTypeAndName(String domain, String type, String name, List<DNSRecord> dnsRecords) {
        String format = StrUtil.format(BASE_URL + "/v1/domains/{}/records/{}/{}", domain, type, name);
        HttpRequest post = HttpUtil.createRequest(Method.PUT, format);
        String body = post
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .contentType("application/json")
                .body(JSONUtil.toJsonStr(dnsRecords))
                .timeout(30 * 1000)
                .execute()
                .body();
        System.out.println(body);
    }


    /**
     * Replace all DNS Records for the specified Domain with the specified Type
     *
     * @param domain     the domain
     * @param type       the type
     * @param dnsRecords the dns records
     */
    public static void replaceAllDNSRecordsWithTheSpecifiedType(String domain, String type, List<DNSRecord> dnsRecords) {
        String format = StrUtil.format(BASE_URL + "/v1/domains/{}/records/{}", domain, type);
        HttpRequest post = HttpUtil.createRequest(Method.PUT, format);
        String body = post
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .contentType("application/json")
                .body(JSONUtil.toJsonStr(dnsRecords))
                .timeout(30 * 1000)
                .execute()
                .body();
        System.out.println(body);
    }

    /**
     * Delete all DNS Records for the specified Domain with the specified Type and Name
     *
     * @param domain     the domain
     * @param type       the type
     */
    public static void deleteDNSRecords(String domain, String type, String name) {
        String format = StrUtil.format(BASE_URL + "/v1/domains/{}/records/{}/{}", domain, type, name);
        HttpRequest post = HttpUtil.createRequest(Method.DELETE, format);
        String body = post
                .setHttpProxy(HOST, PORT)
                .auth(AUTHORIZATION_TOKEN)
                .timeout(30 * 1000)
                .execute()
                .body();
        System.out.println(body);
    }
}
