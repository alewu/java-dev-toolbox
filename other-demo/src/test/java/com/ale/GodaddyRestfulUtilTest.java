package com.ale;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2024/3/18 9:33
 */
class GodaddyRestfulUtilTest {

    private String domainName;

    @BeforeEach
    void init() {
        domainName = "xxx.xyz";
    }

    @Test
    void listDomains() {
        String domains = GodaddyRestfulUtil.listDomains();
        String formatJsonStr = JSONUtil.formatJsonStr(domains);
        System.out.println(formatJsonStr);
        FileUtil.writeString(formatJsonStr, new File("./formatJsonStr.json"), StandardCharsets.UTF_8);
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
        String domains = GodaddyRestfulUtil.getDNSRecord(domainName);
        String formatJsonStr = JSONUtil.formatJsonStr(domains);
        System.out.println(formatJsonStr);
        FileUtil.writeString(formatJsonStr, new File(String.format("./%sDNSRecordFormatJsonStr.json", domainName)), StandardCharsets.UTF_8);
    }

    @Test
    void addDNSRecords() {
        List<DNSRecord> dnsRecords = new ArrayList<>();
        DNSRecord dnsRecord = new DNSRecord();
        dnsRecord.setData("172.23.53.4");
        dnsRecord.setName("privacy");
        dnsRecord.setTtl(600);
        dnsRecord.setType("A");

        DNSRecord webDnsRecord = new DNSRecord();
        webDnsRecord.setData("172.23.53.4");
        webDnsRecord.setName("web");
        webDnsRecord.setTtl(600);
        webDnsRecord.setType("A");

        dnsRecords.add(webDnsRecord);
        GodaddyRestfulUtil.addDNSRecords(domainName, dnsRecords);
    }

    @Test
    void replaceDNSRecords() {

        List<DNSRecord> dnsRecords = new ArrayList<>();
        DNSRecord dnsRecord = new DNSRecord();
        dnsRecord.setData("172.23.53.1");
        dnsRecord.setName("web");
        dnsRecord.setTtl(600);
        dnsRecord.setType("A");

        dnsRecords.add(dnsRecord);
        GodaddyRestfulUtil.replaceDNSRecords(domainName,"A", dnsRecords);

    }
}