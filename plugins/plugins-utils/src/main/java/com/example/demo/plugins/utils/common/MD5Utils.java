package com.example.demo.plugins.utils.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: wulei
 * @date: 2018/7/17
 * @Description:
 */

public class MD5Utils {

    private MD5Utils(){}

    public static String md5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
