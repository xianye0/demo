package com.example.demo.applications.entity;

import com.example.demo.plugins.model.enumtype.AbstractIntValueHolder;
import com.example.demo.plugins.model.enumtype.IntValueHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wulei
 * @date: 2018/7/11
 * @Description:
 */
@AllArgsConstructor
public enum UrlLevelEnum implements IntValueHolder<UrlLevelEnum> {

    NOT_CHECK(0),
    LOGIN(1),
    LOGIN_CHECK(2),
    NORMAL(3);

    @Getter
    private int value;


    /**
     * 通过数值获取枚举
     *
     * @param value
     *            数值
     * @return 枚举
     */
    public static UrlLevelEnum valueOf(int value) {
        return AbstractIntValueHolder.valueOf(UrlLevelEnum.class, value);
    }
}
