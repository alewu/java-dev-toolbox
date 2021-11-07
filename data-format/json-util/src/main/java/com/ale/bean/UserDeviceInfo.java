package com.ale.bean;

/**
 * Copyright 2021 bejson.com
 */

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
    private String isRoot;
    private String isWifi;
    private String availableStorage;
    private String totalStorage;
    private String sensorsNumber;
    private String phonePx;
    private String wifiList;
    private String currentWifi;


}