package com.example.demo.plugins.model.auth;

import lombok.Data;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Data
public class UserEntity {
    private String username;
    private String password;


    public static UserEntity createUser(){
        UserEntity user = new UserEntity();
        user.setPassword("123456");
        user.setUsername("ceshi");
        //        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        for(int i=0;i<1000000;i++){
//            Map map = new HashMap<>();
//            map.put(user,user);
//        }
        return user;
    }
}
