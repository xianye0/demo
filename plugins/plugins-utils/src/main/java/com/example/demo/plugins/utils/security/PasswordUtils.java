package com.example.demo.plugins.utils.security;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.PrivateKey;

/**
 * @author: wulei
 * @date: 2019/4/8
 * @Description:
 */
public class PasswordUtils {
    private KeyPairManager manager;

    public PasswordUtils(KeyPairManager manager){
        this.manager = manager;
    }
    /**
     * 解密
     * @param key
     * @param pwd
     * @return
     */
    public String decrypt(String key, String pwd) {
        PrivateKey p = manager.getPrivateKey(key);
        return RSAUtils.decryptString(p, pwd);
    }

    /**
     * 加密为数据库保存的格式
     * @param userName
     * @param pwd
     * @return
     */
    public String handlePwd(String userName, String pwd) {

        return handlePwd("", userName, pwd, false);
    }

    /**
     * 加密为数据库保存的格式
     * @param key
     * @param userName
     * @param pwd
     * @return
     */
    public String handlePwd(String key, String userName, String pwd) {

        return handlePwd(key, userName, pwd, true);
    }

    /**
     * 加密为数据库保存的格式
     * @param key
     * @param userName
     * @param pwd
     * @return
     */
    public String handlePwd(String key, String userName, String pwd, Boolean isDecrypt) {
        String p = pwd;
        if (isDecrypt) {
            p = decrypt(key, pwd);
            if (p.length() > 50) {
                throw new PrivateKeyExpiredException("解密错误");
            }
        }
        return DigestUtils.sha256Hex(userName + p);
    }
}
