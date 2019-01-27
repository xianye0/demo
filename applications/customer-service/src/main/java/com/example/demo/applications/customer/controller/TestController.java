package com.example.demo.applications.customer.controller;

import com.example.demo.plugins.feign.client.IAuthClient;
import com.example.demo.plugins.model.auth.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
