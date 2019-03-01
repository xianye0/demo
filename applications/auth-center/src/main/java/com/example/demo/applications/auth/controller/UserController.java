package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.AuthService;
import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/29
 * @Description:
 */
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    AuthService authService;

    @PostMapping("add")
    public void addUser(@RequestBody UserEntity user){
        authService.addUser(user);
    }

    @GetMapping("get")
    public UserEntity getUser(String username){
        return authService.getUser(username);
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public List<UserEntity> getList(){
        return authService.getList();
    }
}
