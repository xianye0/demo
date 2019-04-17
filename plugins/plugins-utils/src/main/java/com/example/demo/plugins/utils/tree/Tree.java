package com.example.demo.plugins.utils.tree;

import java.util.List;

/**
 * 树型结构基础类，subList为下级数据列表
 * @author: wulei
 * @date: 2018/11/6
 * @Description:
 */
public interface Tree<T> {



    /**
     * 返回当前编码
     * @return 当前编码
     */
    Object getCode();

    /**
     * 设置当前编码
     * @param code 当前编码
     */
    void setCode(Object code);

    /**
     * 返回父级编码
     * @return 父级编码
     */
    Object getParentCode();

    void setParentCode(Object parentCode);

    /**
     * 返回下级数据列表
     * @return 下级数据列表
     */
    List<T> getChildren();

    /**
     * 设置下级数据列表
     * @param subList 下级数据列表
     */
    void setChildren(List<T> subList);
}
