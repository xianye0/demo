package com.example.demo.applications.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: wulei
 * @date: 2018/7/26
 * @Description:
 */
@Data
public class AuthorityUrl {
    private BigDecimal id;

    private String key;

    private String url;

    private UrlLevelEnum level;

    private String method;

    private String path;


    /**
     * 校验list是否包含url，如果包含则返回url
     * @param urlMap
     * @param url
     * @return
     */
    public static AuthorityUrl checkAuthList(Map<String, AuthorityUrl> urlMap, String url) {
        if (urlMap != null && !urlMap.isEmpty() && urlMap.containsKey(url)) {
            return urlMap.get(url);
        }
        for (Map.Entry<String, AuthorityUrl> auth : urlMap.entrySet()) {
            String u = auth.getKey();
            String regex;
            if (u.indexOf('*') != -1) {
                regex = u.replaceAll("\\*", "[0-9A-Za-z%.]+");
                if (Pattern.matches(regex, url)) {
                    return auth.getValue();
                }
            } else {
                if (url.startsWith(u)) {
                    return auth.getValue();
                }
            }
        }
        return null;
    }
}
