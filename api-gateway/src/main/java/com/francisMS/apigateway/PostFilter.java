package com.francisMS.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@Component
public class PostFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(()->{

            log.error("Global Post Filter enabled.. ");

//            String requestpath = exchange.getRequest().getPath().toString();
//            log.info("post request path => {}", requestpath);
//
//            HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
//
//
//            Set<String> headers = httpHeaders.keySet();
//
//            headers.forEach((header) ->{
//                String headerValue = httpHeaders.getFirst(header);
//                log.info("post {} -> {}", header, headerValue);
//            });
        }));
    }
}
