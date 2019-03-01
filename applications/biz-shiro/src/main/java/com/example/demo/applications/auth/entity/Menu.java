package com.example.demo.applications.auth.entity;

import com.example.demo.plugins.model.entity.OperateBase;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import com.example.demo.plugins.utils.tree.Sortable;
import com.example.demo.plugins.utils.tree.Tree;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/2/1
 * @Description:
 */
@Data
public class Menu extends OperateBase implements Tree<Menu>,Sortable {
    private BigDecimal id;
    private String name;
    private BigDecimal parentId;
    private String authorities;
    private CommonStatusEnum status;
    private String url;
    private String icon;
    private List<Menu> subList;
    private int order;


    public List<String> getAuthorityList(){
        if(authorities == null){
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        for(String auth : authorities.split(",")){
            list.add(auth);
        }
        return list;
    }

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
    public void setSubList(List<Menu> subList) {
        this.subList = subList;
    }




}
