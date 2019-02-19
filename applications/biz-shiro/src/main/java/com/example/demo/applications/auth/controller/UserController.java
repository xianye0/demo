package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.impl.UserServiceImpl;
import com.example.demo.plugins.model.entity.UserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/29
 * @Description:
 */
@RequestMapping("user")
@Controller
public class UserController extends BaseController{
    @Autowired
    UserServiceImpl userService;

    @RequiresPermissions("user:add")
    @PostMapping("add")
    public void addUser(@RequestBody UserEntity user){
        userService.add(user);
    }

    @RequiresPermissions("user:get")
    @GetMapping("get")
    @ResponseBody
    public UserEntity getUser(String username){
        return userService.getByUsername(username);
    }

    @RequiresPermissions("user:list")
    @GetMapping("list")
    @ResponseBody
    public List<UserEntity> getList(){
        return userService.list();
    }
}
