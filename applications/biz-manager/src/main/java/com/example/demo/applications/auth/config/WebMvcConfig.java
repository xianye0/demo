package com.example.demo.applications.auth.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.demo.applications.auth.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author: wulei
 * @date: 2019/4/9
 * @Description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.PrettyFormat);
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSecurityInterceptor()).excludePathPatterns("/security/*").addPathPatterns("/**");
    }

    @Bean
    public SecurityInterceptor getSecurityInterceptor(){
        return new SecurityInterceptor();
    }
}
