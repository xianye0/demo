package com.example.demo.applications.mapper;

import com.example.demo.plugins.model.page.PageParameter;

import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
public interface CrudMapper<E> {
    E get(E e);

    void add(E e);

    List<E> list();

    void delete(E e);

    void mod(E e);

    List<E> page(PageParameter parameter);
}
