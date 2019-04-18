package com.example.demo.applications.controller;

import com.example.demo.applications.entity.OrganizationEntity;
import com.example.demo.applications.service.impl.OrganizationService;
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
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/29
 * @Description:
 */
@RequestMapping("organization")
@RestController
public class OrganizationController extends BaseController {
    @Autowired
    OrganizationService organizationService;

    @PostMapping("add")
    public void add(@RequestBody OrganizationEntity organization) {
        organizationService.add(organization);
    }

    @GetMapping("get/{id}")
    public Responses<OrganizationEntity> get(@PathVariable("id")BigDecimal id) {
        OrganizationEntity organization = new OrganizationEntity();
        organization.setId(id);
        return responsesBuilder.success(organizationService.get(organization));
    }

    @GetMapping("page")
    public Responses<ResultPage<OrganizationEntity>> getPage(PageParameter parameter) {
        return responsesBuilder.success(organizationService.page(parameter));
    }

    @PostMapping("mod")
    public Responses<OrganizationEntity> mod(OrganizationEntity department) {
        organizationService.mod(department);
        return responsesBuilder.success();
    }

    @GetMapping("{status}/{id}")
    public Responses<ResultPage<OrganizationEntity>> changeStatus(@PathVariable("id") BigDecimal id,
            @PathVariable("status") CommonStatusEnum status) {
        organizationService.changeStatus(id, status);
        return responsesBuilder.success();
    }

    @GetMapping("tree")
    public Responses<List<OrganizationEntity>> tree(){
        return responsesBuilder.success(organizationService.tree());
    }
}
