package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.impl.SecurityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@Controller
@RequestMapping("security")
@Slf4j
public class SecurityController {
    @Autowired
    SecurityServiceImpl securityService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("login")
    public String login(){
        return "login";
    }

}
