package com.example.demo.applications.auth.utils;

import com.example.demo.plugins.model.entity.UserEntity;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
public class UserUtils {
    private static ThreadLocal<UserEntity> userMap = new ThreadLocal<>();

    public static void setUser(UserEntity user){
        userMap.set(user);
    }

    public static UserEntity getUser(){
        return userMap.get();
    }
}
