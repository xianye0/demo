package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.entity.Operator;
import com.example.demo.applications.auth.utils.UserUtils;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
public class BaseController {
    @Autowired
    ResponsesBuilder responseBuilder;

    @ModelAttribute
    private void sessionHandler(HttpSession session){
        Operator operator = (Operator) SecurityUtils.getSubject().getPrincipal();
        UserUtils.setUser(operator);
    }


}
