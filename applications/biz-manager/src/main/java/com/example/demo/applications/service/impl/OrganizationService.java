package com.example.demo.applications.service.impl;

import com.example.demo.applications.entity.OrganizationEntity;
import com.example.demo.applications.mapper.OrganizationMapper;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import com.example.demo.plugins.utils.tree.TreeUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description: 部门
 */
@Service
public class OrganizationService extends CrudService<OrganizationMapper, OrganizationEntity> {

    public void changeStatus(BigDecimal id, CommonStatusEnum status) {
        dao.changeStatus(id, status);
    }

    public List<OrganizationEntity> tree() {
        List<OrganizationEntity> list = dao.list();
        return TreeUtils.listToTree(list);
    }
}
