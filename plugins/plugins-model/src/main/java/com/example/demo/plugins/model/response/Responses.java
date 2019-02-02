package com.example.demo.plugins.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: rock
 * @date: 2018/6/8
 * @description: API接口统一输出对象
 */
@Data
@NoArgsConstructor
public class Responses<T> implements Serializable {

    private int code;

    private T data;

    private String desc;

    public Responses(int code, T data, String desc) {
        this.code = code;
        this.data = data;
        this.desc = desc;
    }

    public Responses(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Responses(int code) {
        this.code = code;
    }

    public Responses(int code,T data) {
        this.code = code;
        this.data = data;
    }

    public boolean isSuccess() {
        return code == 0 || code == 1;
    }



}
