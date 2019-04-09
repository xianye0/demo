package com.example.demo.plugins.utils.security;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author: wulei
 * @date: 2019/4/8
 * @Description:
 */
@Data
public class PasswordKey implements Serializable{
    private String publicModulus;
    private String publicExponent;
    private BigInteger privateModulus;
    private BigInteger privateExponent;
    private String key;
}
