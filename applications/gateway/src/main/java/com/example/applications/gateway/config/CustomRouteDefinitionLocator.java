package com.example.applications.gateway.config;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: rock
 * @date: 2019/1/24
 * @Description:  自定义路由
 */
@Component
public class CustomRouteDefinitionLocator implements RouteDefinitionLocator {

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();

        routeDefinitions.add(createRouteDefinition("auth-center","lb://AUTH-CENTER","/security/**"));
        routeDefinitions.add(createRouteDefinition("biz-service","lb://biz-service","/biz/**"));
        routeDefinitions.add(createRouteDefinition("customer-service","lb://customer-service","/customer/**"));
        return Flux.fromIterable(routeDefinitions);
    }

    private RouteDefinition createRouteDefinition(String id,String url,String path){
        RouteDefinition route = new RouteDefinition();
        route.setId(id);
        PredicateDefinition p = new PredicateDefinition();
        p.setName("Path");
        Map<String, String> predicateParams = new HashMap<>(8);
        predicateParams.put("pattern", path);
        p.setArgs(predicateParams);
        List<PredicateDefinition> list = Arrays.asList(p);
        route.setPredicates(list);
        route.setUri(URI.create(url));
        return route;
    }
}
