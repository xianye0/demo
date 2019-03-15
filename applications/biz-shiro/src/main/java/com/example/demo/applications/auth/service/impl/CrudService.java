package com.example.demo.applications.auth.service.impl;

import com.example.demo.applications.auth.mapper.CrudMapper;
import com.example.demo.plugins.model.entity.OperateBase;
import com.example.demo.plugins.model.page.PageParameter;
import com.example.demo.plugins.model.page.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/2/2
 * @Description: 基础service
 */
public abstract class CrudService<D extends CrudMapper, E extends OperateBase> {

    @Autowired
    D dao;

    public void add(E e) {
        dao.add(e);
    }

    public List<E> list() {
        return dao.list();
    }

    public E get(E e) {
        return (E) dao.get(e);
    }

    public void mod(E e) {
        dao.mod(e);
    }

    public ResultPage<E> page(PageParameter parameter) {
        return new ResultPage<>(parameter, dao.list());
    }
}
