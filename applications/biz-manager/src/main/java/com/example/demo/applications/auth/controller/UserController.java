package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.impl.UserService;
import com.example.demo.plugins.model.entity.UserEntity;
import com.example.demo.plugins.model.page.PageParameter;
import com.example.demo.plugins.model.page.ResultPage;
import com.example.demo.plugins.model.response.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
