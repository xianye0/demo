package com.example.demo.applications.customer.handler;

import static com.example.demo.plugins.model.entity.UserEntity.createUser;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.example.demo.plugins.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: rock
 * @date: 2019/1/27
 * @Description:
 */
@Component
public class UserHandler {
    public Mono<ServerResponse> getUser(ServerRequest request) {
        final Mono<UserEntity> person = Mono.just(createUser());
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(person, UserEntity.class);
    }

}
