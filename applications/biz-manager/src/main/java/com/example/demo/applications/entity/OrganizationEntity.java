package com.example.demo.applications.entity;

import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
import com.example.demo.plugins.utils.tree.Tree;
import lombok.Data;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Data
public class OrganizationEntity extends OperateBase implements Tree<OrganizationEntity> {
    private String name;
    private CommonStatusEnum status;
    private OrganizationEntity parent;
    private List<OrganizationEntity> children;
    private UserEntity creator;
    private UserEntity modifier;

    @Override
    public Object getCode() {
        return getId();
    }

    @Override
    public void setCode(Object code) {

    }

    @Override
    public Object getParentCode() {
        if(parent == null){
            return null;
        }else {
            return parent.getCode();
        }
    }

    @Override
    public void setParentCode(Object parentCode) {

    }

    @Override
    public List<OrganizationEntity> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<OrganizationEntity> children) {
        this.children = children;
    }
}
