package com.ale;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

/**
 * @author Administrator
 * @date 2024/3/18 9:33
 */
class GodaddyRestfulUtilTest {

    @Test
    void listDomains() {
        String domains = GodaddyRestfulUtil.listDomains();
        System.out.println(JSONUtil.formatJsonStr(domains));
    }

    @Test
    void available() {
        String body = GodaddyRestfulUtil.available("");
        System.out.println(JSONUtil.formatJsonStr(body));
    }

    @Test
    void suggest() {
        String body = GodaddyRestfulUtil.suggest("");
        System.out.println(JSONUtil.formatJsonStr(body));
    }

    @Test
    void getDomainDetail() {
        String domains = GodaddyRestfulUtil.getDomainDetail("");
        System.out.println(JSONUtil.formatJsonStr(domains));
    }

    @Test
    void getDNSRecord() {
        String domains = GodaddyRestfulUtil.getDNSRecord("");
        System.out.println(JSONUtil.formatJsonStr(domains));
    }

    @Test
    void addDNSRecords() {
        GodaddyRestfulUtil.addDNSRecords("");
    }
}