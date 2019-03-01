package com.example.demo.applications.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: rock
 * @date: 2019/2/20
 * @Description:
 */

@Controller
public class DemoController {

    @GetMapping("/demo/{path}")
    public String index(@PathVariable("path") String path){
        return "/demo/"+path;
    }

    @GetMapping("/test/{path}")
    public String test(@PathVariable("path") String path){
        return "/test/"+path;
    }

}
