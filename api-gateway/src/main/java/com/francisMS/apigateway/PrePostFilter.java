package com.francisMS.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class PrePostFilter {

    @Order(2) //ordre of pre filter is inversely proportional to post filter ordering.
    // This only applies to filters in same class
    @Bean
    public GlobalFilter firstGlobalFilter(){
        return (exchange, chain) -> {

            log.error("first pre filter");

            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                log.error("first post filter");
            }));
        };
    }

    @Order(1)
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
