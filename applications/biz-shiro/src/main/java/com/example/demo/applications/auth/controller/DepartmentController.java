package com.example.demo.applications.auth.controller;

import com.example.demo.applications.auth.service.IDepartmentService;
import com.example.demo.plugins.model.entity.DepartmentEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/29
 * @Description:
 */
@RequestMapping("department")
@RestController
public class DepartmentController extends BaseController{
    @Autowired
    IDepartmentService departmentService;


    @PostMapping("add")
    public void addUser(@RequestBody DepartmentEntity department){
        departmentService.add(department);
    }

    @GetMapping("get")
    public DepartmentEntity get(DepartmentEntity departmentEntity){
        return departmentService.get(departmentEntity);
    }

    @RequiresPermissions("department:list")
    @GetMapping("list")
    public List<DepartmentEntity> getList(){
        return departmentService.list();
    }
}
