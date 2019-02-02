package com.example.demo.applications.auth.entity;

import com.example.demo.plugins.model.entity.OperateBase;
import com.example.demo.plugins.model.enumtype.CommonStatusEnum;
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
public class Permission extends OperateBase {
    private BigDecimal id;
    private String name;
    private String authorities;
    private CommonStatusEnum status;

    public List<Authority> getAutorityList(){
        if(authorities == null){
            return Collections.emptyList();
        }
        List<Authority> list = new ArrayList<>();
        for(String auth : authorities.split(",")){
            list.add(new Authority(auth));
        }
        return list;
    }
}
