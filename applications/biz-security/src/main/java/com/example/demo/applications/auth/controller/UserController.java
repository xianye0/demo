package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.entity.Operator;
import com.example.demo.applications.auth.service.IUserService;
import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    IUserService userService;

    @PreAuthorize("hasAuthority('USER_ADD')")
    @PostMapping("add")
    public void addUser(@RequestBody UserEntity user){
        userService.add(user);
    }

    @PreAuthorize("hasAuthority('USER_GET')")
    @GetMapping("get")
    @ResponseBody
    public UserEntity getUser(String username){
        return userService.getByUsername(username);
    }

    @PreAuthorize("hasAuthority('user:list')")
    @GetMapping("list")
    @ResponseBody
    public List<UserEntity> getList(HttpSession session){
        Operator operator = (Operator) SecurityContextHolder.getContext().getAuthentication();
        return userService.list();
    }
}
