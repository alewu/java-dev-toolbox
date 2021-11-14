package com.ale.bean;

/**
 * Copyright 2021 bejson.com
 */

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class UserDeviceInfo {

    private String deviceId;
    private String os;
    private String osVersion;
    private String operators;
    private String brand;
    private String phoneType;
    private String deviceName;
    private String isVmware;
    @JSONField(name = "is_root")
    private String isRoot;
    @JSONField(name = "is_wifi")
    private String isWifi;
    @JSONField(name = "available_storage")
    private String availableStorage;
    @JSONField(name = "available_storage")
    private String totalStorage;
    @JSONField(name = "sensors_number")
    private String sensorsNumber;
    @JSONField(name = "phone_px")
    private String phonePx;
    private String wifiList;
    private String currentWifi;


}