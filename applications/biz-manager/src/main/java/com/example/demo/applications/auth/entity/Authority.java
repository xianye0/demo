package com.example.demo.applications.auth.entity;

import com.example.demo.plugins.model.entity.OperateBase;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import com.example.demo.plugins.utils.tree.Sortable;
import com.example.demo.plugins.utils.tree.Tree;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/2/1
 * @Description:
 */
@Data
public class Authority extends OperateBase implements Tree<Authority>,Sortable {
    private BigDecimal id;
    private String name;
    private BigDecimal parentId;
    private String authorities;
    private CommonStatusEnum status;
    private String url;
    private String icon;
    private List<Authority> subList;
    private int type;
    private int order;

    @Override
    public Object getCode() {
        return id;
    }

    @Override
    public void setCode(Object code) {
        this.id = (BigDecimal) code;
    }

    @Override
    public Object getParentCode() {
        return this.parentId;
    }

    @Override
    public void setParentCode(Object parentCode) {
        this.parentId = (BigDecimal)parentCode;
    }

    @Override
    public void setSubList(List<Authority> subList) {
        this.subList = subList;
    }




}
