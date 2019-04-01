package com.example.demo.plugins.model.page;

import lombok.Data;

import java.util.List;

/**
 * @author: wulei
 * @date: 2019/3/11
 * @Description:
 */
@Data
public class ResultPage <T>{
    private PageEntity page;
    private List<T> data;

    public ResultPage(PageEntity page,List list){
        this.page = page;
        this.data = list;
    }

}
