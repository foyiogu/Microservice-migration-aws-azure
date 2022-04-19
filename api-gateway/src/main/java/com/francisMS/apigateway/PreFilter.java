package com.francisMS.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@Component
public class PreFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.error("Pre Filter started...");

//        String requestpath = exchange.getRequest().getPath().toString();
//        log.info("pre request path => {}", requestpath);
//
//        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
//
//
//        Set<String> headers = httpHeaders.keySet();
//
//        headers.forEach((header) ->{
//            String headerValue = httpHeaders.getFirst(header);
//            log.info("pre {} -> {}", header, headerValue);
//        });

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
