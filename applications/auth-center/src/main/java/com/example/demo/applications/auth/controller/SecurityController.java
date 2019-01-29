package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.AuthService;
import com.example.demo.plugins.model.auth.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@RestController
@RequestMapping("security")
@Slf4j
public class SecurityController {
    @Autowired
    AuthService authService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("login")
    public UserEntity login(UserEntity user){
        redisTemplate.opsForValue().set("abc","aaa");
        log.info("",redisTemplate.opsForValue().get("abc"));
        return authService.getUser(user.getUsername());
    }

    @GetMapping("status")
    public String status(){
        return "start";
    }



}
