package com.example.demo.applications.auth.config;

import com.example.demo.applications.auth.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
//@Configuration
public class InterceptorAppConfigurer  implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());     //拦截的对象会进入这个类中进行判断
//        registration.addPathPatterns("/**");                    //所有路径都被拦截
        registration.excludePathPatterns("/security/**","/login","/error","/static/**","/logout");       //添加不拦截路径

    }
}
