package com.example.demo.applications.customer.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.example.demo.applications.customer.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author: wulei
 * @date: 2019/1/27
 * @Description: 使用route方式注册接口，与使用注解方式效果相同，知识采用lambda表达式来实现。
 */
@Configuration
public class WebConfig {
    @Bean
    public RouterFunction<ServerResponse> route(UserHandler userHandler) {
        return RouterFunctions.route(GET("/people"), userHandler::getUser);
    }
}
