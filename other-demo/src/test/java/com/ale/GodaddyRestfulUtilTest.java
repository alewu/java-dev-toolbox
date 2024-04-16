package com.ale;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2024/3/18 9:33
 */
class GodaddyRestfulUtilTest {

    private String domainName;

    private String dateTimeStr;

    @BeforeEach
    void init() {
        domainName = "xxx.com";
        dateTimeStr = DateUtil.format(DateUtil.date(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Test
    void listDomains() {
        String domains = GodaddyRestfulUtil.listDomains();
        String formatJsonStr = JSONUtil.formatJsonStr(domains);
        System.out.println(formatJsonStr);
        String pathname = String.format("./domains_%s_%s.json", "Godaddy", dateTimeStr);
        FileUtil.writeString(formatJsonStr, new File(pathname), StandardCharsets.UTF_8);
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

    @ParameterizedTest
    @ValueSource(strings = {"xxx.com"})
    void getDomainDetail(String domainName) {
        String domains = GodaddyRestfulUtil.getDomainDetail(domainName);
        System.out.println(JSONUtil.formatJsonStr(domains));
    }

    @ParameterizedTest
    @ValueSource(strings = {"xxx.cc"})
    void getDNSRecord(String domainName) {
        String domains = GodaddyRestfulUtil.getAllDNSRecord(domainName);
        String formatJsonStr = JSONUtil.formatJsonStr(domains);
        System.out.println(formatJsonStr);
        String pathname = String.format("./dns_records_%s_%s.json", domainName, dateTimeStr);
        FileUtil.writeString(formatJsonStr, new File(pathname), StandardCharsets.UTF_8);
    }


    @ParameterizedTest
    @ValueSource(strings = {"arctco-technology-ltd.com"})
    void retrieveDNSRecordsOptionallyWithTheSpecifiedType(String domainName) {
        String type = "A";
        String name = "";
        String dnsRecords = GodaddyRestfulUtil.retrieveDNSRecordsOptionallyWithTheSpecifiedType(domainName, type, name);

        String formatJsonStr = JSONUtil.formatJsonStr(dnsRecords);
        System.out.println(formatJsonStr);
        String pathname = String.format("./dns_records_%s_%s_%s.json", domainName, type, dateTimeStr);
        FileUtil.writeString(formatJsonStr, new File(pathname), StandardCharsets.UTF_8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"xxx.xyz"})
    void addDNSRecords(String domainName) {
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
    void addTextDNSRecords() {
        String type = "TXT";

        List<DNSRecord> dnsRecords = new ArrayList<>();
        DNSRecord dnsRecord = new DNSRecord();
        dnsRecord.setData("2023052200000049");
        dnsRecord.setName("_dnsauth.xx");
        dnsRecord.setTtl(600);
        dnsRecord.setType(type);

        dnsRecords.add(dnsRecord);
        GodaddyRestfulUtil.addDNSRecords(domainName, dnsRecords);
    }

    @Test
    void replaceAllDNSRecordsWithTheSpecifiedType() {

        String type = "A";

        List<DNSRecord> dnsRecords = new ArrayList<>();
        DNSRecord dnsRecord = new DNSRecord();
        dnsRecord.setData("172.23.53.1");
        dnsRecord.setName("web");
        dnsRecord.setTtl(600);
        dnsRecord.setType("A");

        dnsRecords.add(dnsRecord);
        GodaddyRestfulUtil.replaceAllDNSRecordsWithTheSpecifiedType(domainName, type, dnsRecords);

    }

    @Test
    void deleteDNSRecords() {
        String type = "A";
        String name = "web";
        GodaddyRestfulUtil.deleteDNSRecords(domainName, type, name);
    }

}