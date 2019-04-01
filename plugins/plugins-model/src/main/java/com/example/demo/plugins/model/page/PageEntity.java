package com.example.demo.plugins.model.page;

import lombok.Data;

/**
 * @author: wulei
 * @date: 2019/3/11
 * @Description:
 */
@Data
public class PageEntity {
    private int pageIndex;
    private int pageSize;
    private int pageCount;
    private int total;

    public void setTotal(int total) {
        this.pageCount = Double.valueOf(Math.ceil(total*1.0/pageSize)).intValue();
        this.total = total;
    }
}
