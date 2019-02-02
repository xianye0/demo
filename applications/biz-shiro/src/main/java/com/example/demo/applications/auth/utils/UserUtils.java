package com.example.demo.applications.auth.utils;

import com.example.demo.applications.auth.entity.Operator;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
public class UserUtils {
    private static ThreadLocal<Operator> userMap = new ThreadLocal<>();

    public static void setUser(Operator user){
        userMap.set(user);
    }

    public static Operator getUser(){
        return userMap.get();
    }
}
