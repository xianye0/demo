package com.example.demo.plugins.model.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: rock
 * @date: 2018/8/2
 * @Description:
 */
@AllArgsConstructor
public enum LocaleTypeEnum {
    //系统错误码
    SYS_CODE("messageError.code."),
    //设备异常错误码
    DEVICE_FAULT("deviceFault.code."),
    //设备终端
    CLIENT_APPKEY("appkey.code.");

    @Getter
    private String value;
}
