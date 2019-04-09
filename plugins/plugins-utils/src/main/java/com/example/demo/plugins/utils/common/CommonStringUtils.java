package com.example.demo.plugins.utils.common;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author: wulei
 * @date: 2018/7/5
 * @Description: 字符串通用工具类
 */
public final class CommonStringUtils {

    private static SecureRandom random = new SecureRandom();
    private CommonStringUtils(){}

    /**
     * 替换模板字符串中的参数
     *
     * @param str
     * @param params
     * @return
     */
    public static String format(String str, String... params) {
        for(int i=0;i<params.length;i++){
            str = str.replaceAll("\\{"+i+"}",params[i]);
        }

        return str;
    }

    public static String createToken(){
        String ran = getRandomString(30);
        String pre = Base64.getEncoder().encodeToString((ran).getBytes());
        String suf = MD5Utils.md5(ran + System.currentTimeMillis());
        return pre + "." + suf;
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
