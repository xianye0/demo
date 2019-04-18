package com.example.demo.applications.controller;

import com.example.demo.applications.entity.RoleEntity;
import com.example.demo.applications.service.impl.RoleService;
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
 * @author: wulei
 * @date: 2019/4/15
 * @Description:
 */
@RequestMapping("role")
@RestController
public class RoleController extends BaseController {
    @Autowired
    RoleService roleService;

    @PostMapping("add")
    public void add(@RequestBody RoleEntity role) {
        roleService.add(role);
    }

    @PostMapping("mod")
    public void mod(@RequestBody RoleEntity role) {
        roleService.mod(role);
    }

    @GetMapping("list")
    public Responses<RoleEntity> list() {
        return responsesBuilder.success(roleService.list());
    }

    @GetMapping("page")
    public Responses<ResultPage<RoleEntity>> getPage(PageParameter parameter) {
        return responsesBuilder.success(roleService.page(parameter));
    }

    @GetMapping("get/{id}")
    public Responses<RoleEntity> get(@PathVariable("id") BigDecimal id) {
        RoleEntity r = new RoleEntity();
        r.setId(id);
        return responsesBuilder.success(roleService.get(r));
    }

    @GetMapping("getAuthorityList/{id}")
    public Responses getAuthorityList(BigDecimal id) {
        return responsesBuilder.success(roleService.getAuthorityList(id));
    }


}
