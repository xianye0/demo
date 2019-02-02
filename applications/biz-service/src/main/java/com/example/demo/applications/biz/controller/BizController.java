package com.example.demo.applications.biz.controller;

import static com.example.demo.plugins.model.entity.UserEntity.createUser;

import com.example.demo.plugins.feign.client.IAuthClient;
import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: rock
 * @date: 2019/1/26
 * @Description:
 */
@RequestMapping("biz")
@RestController
public class BizController {

    @Autowired
    IAuthClient authClient;

    @GetMapping("getUser")
    public UserEntity getUser(){
        return authClient.getUser();
    }

    @GetMapping("getUser1")
    public UserEntity test(){
        return createUser();
    }

}
