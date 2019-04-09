package com.example.demo.plugins.utils.security;

/**
 * @author: wulei
 * @date: 2019/4/8
 * @Description: 密钥失效
 */

public class PrivateKeyExpiredException extends RuntimeException {
    public PrivateKeyExpiredException(String errorMsg, Throwable e){
        super(errorMsg,e);
    }

    public PrivateKeyExpiredException(String errorMsg){
        super(errorMsg);
    }

    public PrivateKeyExpiredException(Throwable e){
        super(e);
    }
}
