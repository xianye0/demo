package com.example.demo.applications.auth.service;

import com.example.demo.plugins.model.response.Responses;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
public interface ISecurityService {
    Responses login(String username,String password);
}
