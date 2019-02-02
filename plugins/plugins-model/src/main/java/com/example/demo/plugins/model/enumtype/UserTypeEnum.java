package com.example.demo.plugins.model.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
@AllArgsConstructor
public enum  UserTypeEnum implements IntValueHolder<UserTypeEnum> {
    ADMINISTRATOR(0),   //管理员
    BUSINESS(1);    //业务人员

    @Getter
    private int value;
    /**
     * 通过数值获取枚举
     *
     * @param value
     *            数值
     * @return 枚举
     */
    public static UserTypeEnum valueOf(int value) {
        return AbstractIntValueHolder.valueOf(UserTypeEnum.class, value);
    }
}
