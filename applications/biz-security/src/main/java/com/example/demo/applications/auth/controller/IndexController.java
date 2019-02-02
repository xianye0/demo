package com.example.demo.applications.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: rock
 * @date: 2019/2/2
 * @Description:
 */
@Controller
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "index";
    }
}
