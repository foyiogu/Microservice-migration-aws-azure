package com.francisMS.apigateway;

import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Francis Oyiogu
 * @created_at
 */

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    public static class Config{

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//
//            if (!request.getHeaders().containsKey("Authorization")){
//                return onError(exchange, "No auth header");
//            }
//
//            String jwt = request.getHeaders().get("Authorization").get(0).substring(7);
//            String jwt2 = request.getHeaders().get("Authorization").get(0).replace("Bearer ","");
//            System.out.println(jwt + jwt2);
//
//            if (!isJwtValid(jwt))return onError(exchange, "Jwt Token not valid");
            return chain.filter(exchange);
        };
    }
    private Mono<Void> onError(ServerWebExchange exchange, String err){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        return response.setComplete();
    }

    private boolean isJwtValid(String jwt){
        boolean returnValue = jwt.equals("bef");
//
//        String subject = null;
//        try {
//            subject = Jwts.parser().setSigningKey("abc").parseClaimsJws(jwt)
//                    .getBody().getSubject();
//        }catch (Exception ex){
//            returnValue = false;
//        }
//        if (subject.isEmpty() || subject == null) returnValue = false;

        return returnValue;
    }
}
