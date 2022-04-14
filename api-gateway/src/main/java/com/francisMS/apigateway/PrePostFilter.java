package com.francisMS.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class PrePostFilter {

    @Bean
    public GlobalFilter firstGlobalFilter(){
        return (exchange, chain) -> {

            log.error("first pre filter");

            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                log.error("first post filter");
            }));
        };
    }

    @Bean
    public GlobalFilter secondGlobalFilter(){
        return (exchange, chain) -> {

            log.error("second pre filter");

            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                log.error("second post filter");
            }));
        };
    }
}
