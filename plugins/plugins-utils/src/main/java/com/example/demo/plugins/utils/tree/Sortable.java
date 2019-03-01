package com.example.demo.plugins.utils.tree;

/**
 * 对tree排序需要实现该接口
 * @author: wulei
 * @date: 2018/11/6
 * @Description:
 */
public interface Sortable {
    /**
     * 获取编号
     * @return 序号，根据该值从小到大排列
     */
    int getOrder();
}
