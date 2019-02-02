package com.example.demo.applications.auth;

import com.example.demo.plugins.model.entity.UserEntity;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;

import java.math.BigDecimal;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
public class TestDataUtils {
    public static UserEntity createUser(){
        UserEntity user = new UserEntity();
        user.setUsername("test");
        user.setPassword("1234");
        user.setId(BigDecimal.ONE);
        user.setStatus(CommonStatusEnum.ENABLE);
        return user;
    }
}
