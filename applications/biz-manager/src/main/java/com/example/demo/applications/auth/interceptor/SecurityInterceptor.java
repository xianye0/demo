package com.example.demo.applications.auth.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo.applications.auth.entity.constants.SecurityConstants;
import com.example.demo.applications.auth.service.impl.SecurityService;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import com.example.demo.plugins.model.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: wulei
 * @date: 2019/4/9
 * @Description:
 */
@Component
@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {
    @Autowired
    SecurityService securityService;
    @Autowired
    ResponsesBuilder responsesBuilder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader(SecurityConstants.ACCESSTOKEN);
        int code = securityService.checkAuth(token, request.getRequestURI());
        if (code != ResponseCode.SUCCESS) {
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.print(JSON.toJSONString(responsesBuilder.fullCodeError(code)));
            } catch (IOException e) {
                log.error("response error", e);
            }
            return false;
        }

        return true;

    }


}
