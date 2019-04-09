package com.example.demo.plugins.utils.security;

import java.security.interfaces.RSAPrivateKey;

/**
 * @author: wulei
 * @date: 2019/4/8
 * @Description:
 */
public interface KeyPairManager {
    /**
     * 获取私钥
     * @param key
     * @return
     */
    RSAPrivateKey getPrivateKey(String key);

    /**
     * 获取密钥
     * @param key
     * @return
     */
    PasswordKey getPasswordKey(String key);

    /**
     * 创建密钥
     * @param key
     */
    PasswordKey createKeyPair(String key);

    PasswordKey getPublicKey(String key);
}
