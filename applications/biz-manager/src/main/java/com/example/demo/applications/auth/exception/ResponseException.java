package com.example.demo.applications.auth.exception;

/**
 * @author: wulei
 * @date: 2019/4/8
 * @Description:
 */
public class ResponseException extends RuntimeException {
    private int code;

    private String desc;

    private Object[] arr;

    private Object data;

    public ResponseException(int code){
        this.code = code;
    }

    public ResponseException(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public ResponseException(int code,Object[] arr){
        this.code = code;
        this.arr = arr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object[] getArr() {
        return arr;
    }

    public void setArr(Object[] arr) {
        this.arr = arr;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
