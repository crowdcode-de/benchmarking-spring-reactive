package io.crowdcode.benchmark.blocking.cddb;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


/**
 * Workaround for Ticket https://github.com/spring-projects/spring-boot/issues/9785
 */
@Component
public class WelcomeWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange.getRequest().getURI().getPath().equals("/")) {
            return chain
                    .filter(exchange
                            .mutate()
                            .request(exchange
                                    .getRequest()
                                    .mutate()
                                    .path("/index.html")
                                    .build())
                            .build());
        }
        return chain.filter(exchange);
    }
}
