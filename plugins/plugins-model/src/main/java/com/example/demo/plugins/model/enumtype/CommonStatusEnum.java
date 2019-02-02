package com.example.demo.plugins.model.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description: 通用状态
 */
@AllArgsConstructor
public enum CommonStatusEnum implements IntValueHolder<CommonStatusEnum>{
    DISABLE(0),
    ENABLE(1),
    DELETE(2);

    @Getter
    private int value;

    /**
     * 通过数值获取枚举
     *
     * @param value
     *            数值
     * @return 枚举
     */
    public static CommonStatusEnum valueOf(int value) {
        return AbstractIntValueHolder.valueOf(CommonStatusEnum.class, value);
    }

}
