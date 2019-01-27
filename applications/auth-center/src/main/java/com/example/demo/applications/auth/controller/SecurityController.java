package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.AuthService;
import com.example.demo.plugins.model.auth.UserEntity;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
        Mono<UserEntity> m = Mono.just(createUser());
        m.subscribe(System.out::println);
        return m;
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

        try {
            System.out.println("--------------"+Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
