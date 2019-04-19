package com.example.demo.applications.controller;

import com.example.demo.applications.entity.Operator;
import com.example.demo.applications.entity.constants.SecurityConstants;
import com.example.demo.applications.service.impl.SecurityService;
import com.example.demo.plugins.model.response.Responses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class SecurityController extends BaseController {
    @Autowired
    SecurityService securityService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("getPublicKey")
    public Responses getPublicKey() {
        return responsesBuilder.success(securityService.getPublicKey());
    }

    @PostMapping("login")
    public Responses login(String username, String password, String key) {

        return responsesBuilder.success(securityService.login(username, password, key));
    }

    /**
     * 获取菜单
     * @param token
     * @return
     */
    @GetMapping("queryMenuList")
    public Responses queryMenuList(@CookieValue(SecurityConstants.ACCESSTOKEN) String token) {
        return responsesBuilder.success(securityService.queryMenuList(token));
    }


    @GetMapping("logout")
    public Responses logout(@CookieValue(SecurityConstants.ACCESSTOKEN) String token) {
        securityService.logout(token);
        return responsesBuilder.success();
    }

    @GetMapping("getOperator")
    public Responses<Operator> getOperator(@CookieValue(SecurityConstants.ACCESSTOKEN) String token){

        return responsesBuilder.success(securityService.getOperator(token));
    }

    @PostMapping("modOperator")
    public Responses modOperator(@CookieValue(SecurityConstants.ACCESSTOKEN) String token,@RequestBody Operator operator){
        securityService.modOperator(token,operator);
        return responsesBuilder.success();
    }

}
