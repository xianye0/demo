package com.example.demo.plugins.utils.security;


import com.example.demo.plugins.utils.memory.CacheMap;
import com.example.demo.plugins.utils.memory.ICacheMap;
import org.apache.commons.codec.binary.Hex;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Created by WULEI on 2017/7/21.
 * @version 1.0
 */
public class DefaultKeyPairManager implements KeyPairManager {
    private static final int TIMEOUT = 12; //失效时间
    private ICacheMap<String,PasswordKey> map ;
    public DefaultKeyPairManager(ICacheMap map){
        this.map = map;
    }

    /**
     * 获取私钥
     * 如果密钥被使用，10分钟后过期
     * @param key
     * @return
     */
    public RSAPrivateKey getPrivateKey(String key) {

        try {
            PasswordKey passwordKey =  map.get(key);
            return RSAUtils.generateRSAPrivateKey(passwordKey.getPrivateModulus(), passwordKey.getPrivateExponent());
        } catch (NullPointerException | IllegalArgumentException | InvalidKeySpecException e) {
            throw new PrivateKeyExpiredException(e);
        }
    }

    /**
     * 获取公钥
     * @param key
     * @return
     */
    public PasswordKey getPasswordKey(String key) {
        if(!map.contains(key)){
            createKeyPair(key);
        }
        return map.get(key);
    }

    @Override
    public PasswordKey getPublicKey(String key) {
        PasswordKey k = getPasswordKey(key);
        PasswordKey pub = new PasswordKey();
        pub.setPublicModulus(k.getPublicModulus());
        pub.setPublicExponent(k.getPublicExponent());
        pub.setKey(key);
        return pub;
    }

    /**
     * 生成密钥放入缓存中
     * @param key
     */
    public PasswordKey createKeyPair(String key) {

        KeyPair kp = RSAUtils.getKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        String publicM = new String(Hex.encodeHex(publicKey.getModulus().toByteArray()));
        String publicP = new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray()));
        BigInteger privateM = privateKey.getModulus();
        BigInteger privateP = privateKey.getPrivateExponent();
        PasswordKey passwordKey = new PasswordKey();
        passwordKey.setKey(key);
        passwordKey.setPrivateExponent(privateP);
        passwordKey.setPrivateModulus(privateM);
        passwordKey.setPublicExponent(publicP);
        passwordKey.setPublicModulus(publicM);
        map.put(key,passwordKey,TIMEOUT * 3600 * 1000);
        return passwordKey;
    }



}
