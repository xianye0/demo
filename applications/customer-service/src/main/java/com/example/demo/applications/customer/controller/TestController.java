package com.example.demo.applications.customer.controller;

import static com.example.demo.plugins.model.auth.UserEntity.createUser;

import com.example.demo.plugins.feign.client.IAuthClient;
import com.example.demo.plugins.model.auth.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author: wulei
 * @date: 2019/1/27
 * @Description:
 */
@RequestMapping("customer")
@RestController
public class TestController {
    @Autowired
    IAuthClient authClient;
    @GetMapping("getUser")
    public UserEntity getUser(){
        return authClient.getUser();
    }

    @GetMapping("getUser1")
    public Mono<UserEntity> test(){
        return Mono.just(createUser());
    }
}
