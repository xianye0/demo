package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.impl.DepartmentService;
import com.example.demo.plugins.model.entity.DepartmentEntity;
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
@RequestMapping("department")
@RestController
public class DepartmentController extends BaseController{
    @Autowired
    DepartmentService departmentService;

    @PostMapping("add")
    public void addUser(@RequestBody DepartmentEntity department){
        departmentService.add(department);
    }

    @GetMapping("get")
    public DepartmentEntity get(DepartmentEntity departmentEntity){
        return departmentService.get(departmentEntity);
    }

    @GetMapping("page")
    public Responses<ResultPage<DepartmentEntity>> getList(PageParameter parameter){
        return responsesBuilder.success(departmentService.page(parameter));
    }
}
