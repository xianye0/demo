package com.example.demo.applications.service.impl;

import com.example.demo.applications.entity.Authority;
import com.example.demo.applications.entity.RoleEntity;
import com.example.demo.applications.mapper.AuthorityMapper;
import com.example.demo.applications.mapper.RoleMapper;
import com.example.demo.plugins.utils.tree.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wulei
 * @date: 2019/4/15
 * @Description:
 */
@Service
public class RoleService extends CrudService<RoleMapper,RoleEntity> {
    @Autowired
    AuthorityMapper authorityMapper;

    public List<Authority> getAuthorityList(BigDecimal id){
        List<Authority> allList = authorityMapper.getAllAuthorityList();
        List<String> authCodes = authorityMapper.getAuthorityCodeList(id);
        return packageAuth(allList,authCodes);
    }


    /**
     * 将权限封装成树型结构
     * @param allList
     * @param menuCodes
     * @return
     */
    private List<Authority> packageAuth(List<Authority> allList, List<String> menuCodes) {
        //设置勾选按钮
        setMenuChecked(allList, menuCodes);

        return TreeUtils.listToTree(allList);
    }

    /**
     * 设置菜单是否勾选,新增时权限树全部勾选
     * @param allList
     * @param list
     */
    private void setMenuChecked(List<Authority> allList, List<String> list) {
        //根据分销商权限设置禁用权限
        Map<String, String> map = new HashMap();
        for (String menuCode : list) {
            map.put(menuCode, null);
        }
        for (Authority menu : allList) {
            //如果当前权限包含则checked=true
            if (map.containsKey(menu.getCode()) || map.isEmpty()) {
                menu.setChecked(true);
            }
        }
    }
}
