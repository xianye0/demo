package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.AuthService;
import com.example.demo.plugins.model.auth.UserEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: rock
 * @date: 2019/1/23
 * @Description:
 */
@RestController
@RequestMapping("security")
public class SecurityController {
    @Autowired
    AuthService authService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("login")
    public UserEntity login(UserEntity user){
        redisTemplate.opsForValue().set("abc","aaa");
        System.out.println(redisTemplate.opsForValue().get("abc"));
        return authService.getUser(user.getUsername());
    }

    @GetMapping("status")
    public String status(){
        return "start";
    }


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
//        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);

        for(int i=0;i<1000000;i++){
            Map map = new HashMap<>();
            map.put(user,user);
        }
        return user;
    }

    public List<UserEntity> createUsers(){
        List l = new ArrayList();
        for(int i=0;i<10;i++){
            l.add(createUser());
        }
        return l;
    }



    @GetMapping("getUser2")
    public Flux<UserEntity> getUser2(){
        return Flux.just(createUser());
    }
}
