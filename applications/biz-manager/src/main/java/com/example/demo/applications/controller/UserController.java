package com.example.demo.applications.controller;

import com.example.demo.applications.entity.UserEntity;
import com.example.demo.applications.service.impl.UserService;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import com.example.demo.plugins.model.page.PageParameter;
import com.example.demo.plugins.model.page.ResultPage;
import com.example.demo.plugins.model.response.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author: rock
 * @date: 2019/1/29
 * @Description:
 */
@RequestMapping("user")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @PostMapping("add")
    public void addUser(@RequestBody UserEntity user) {
        userService.add(user);
    }

    @GetMapping("get")
    public UserEntity getUser(String username) {
        return userService.getByUsername(username);
    }

    @GetMapping("page")
    public Responses<ResultPage<UserEntity>> getPage(PageParameter parameter) {
        return responsesBuilder.success(userService.page(parameter));
    }

    @GetMapping("{status}/{id}")
    public Responses changeStatus(@PathVariable("id") BigDecimal id, @PathVariable("status") CommonStatusEnum status) {
        userService.changeStatus(id, status);
        return responsesBuilder.success();
    }

    @PostMapping("mod")
    public Responses mod(@RequestBody UserEntity user){
        userService.mod(user);
        return responsesBuilder.success();
    }
}
