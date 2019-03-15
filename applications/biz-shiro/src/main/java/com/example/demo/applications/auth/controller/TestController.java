package com.example.demo.applications.auth.controller;

import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: rock
 * @date: 2019/1/29
 * @Description:
 */
@RequestMapping("test1")
@RestController
public class TestController {
    @GetMapping("getUser")
    public Mono<UserEntity> getUser(){
        return Mono.just(createUser());
    }

    @GetMapping("getUser1")
    public UserEntity getUser1(){
        return createUser();
    }

    public UserEntity createUser(){
        UserEntity user = new UserEntity();
        user.setPassword("123456");
        user.setUsername("ceshi");

        for(int i=0;i<1000000;i++){
            Map map = new HashMap<>();
            map.put(user,user);
        }
        return user;
    }


    @GetMapping("getUser2")
    public Flux<UserEntity> getUser2(){
        return Flux.just(createUser());
    }
}
