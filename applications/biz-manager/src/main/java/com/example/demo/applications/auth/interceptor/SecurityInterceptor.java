package com.example.demo.applications.auth.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo.applications.auth.entity.AuthorityUrl;
import com.example.demo.applications.auth.entity.UrlLevelEnum;
import com.example.demo.applications.auth.entity.constants.SecurityConstants;
import com.example.demo.applications.auth.service.impl.SecurityService;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import com.example.demo.plugins.model.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wulei
 * @date: 2019/4/9
 * @Description:
 */
@Component
@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {
    @Value("${url.timeOut:3600}")
    private int timeOut;
    private long allTime = 0;
    private Map<String, AuthorityUrl> allMap = new ConcurrentHashMap<>();
    @Autowired
    SecurityService securityService;
    @Autowired
    ResponsesBuilder responsesBuilder;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        AuthorityUrl url = AuthorityUrl.checkAuthList(getUrlMap(), request.getRequestURI()+":"+request.getMethod());
        if (url == null) {
            setAuthFailed(response, 404, ResponseCode.TOKEN_INVALID);
            return false;
        }
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals(SecurityConstants.ACCESSTOKEN)) {
                    token = c.getValue();
                    break;
                }
            }
        }
        if (url.getLevel() == UrlLevelEnum.LOGIN_CHECK) {
            if (token == null) {
                setAuthFailed(response, 401, ResponseCode.TOKEN_INVALID);
            }
        } else if (url.getLevel() == UrlLevelEnum.NORMAL) {
            if (token != null) {
                int code = securityService.checkAuth(token, url.getKey());
                if (code != ResponseCode.SUCCESS) {
                    setAuthFailed(response, 401, code);
                    return false;
                }
            } else {
                setAuthFailed(response, 401, ResponseCode.TOKEN_INVALID);
                return false;
            }
        }
        return true;
    }


    private void setAuthFailed(HttpServletResponse response, int status, int code) {
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSON.toJSONString(responsesBuilder.fullCodeError(code)));
        } catch (IOException e) {
            log.error("response error", e);
        }
    }

    private Map<String, AuthorityUrl> getUrlMap() {
        if (allTime + timeOut * 1000 < System.currentTimeMillis()) {
            allMap.clear();
            allMap.putAll(securityService.queryUrlMap());
            allTime = System.currentTimeMillis();
        }
        return allMap;
    }

}
