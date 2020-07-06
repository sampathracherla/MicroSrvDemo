package com.msdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.WebFilter;

@Configuration
public class MSAccountConfig {

	@Bean
	public WebFilter contextPathWebFilter() {
		String contextPath = "/accounts";
		return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getURI().getPath().startsWith(contextPath)) {
                return chain.filter(
                    exchange.mutate()
                    .request(request.mutate().contextPath(contextPath).build())
                    .build());
            }
            return chain.filter(exchange);
        };
	}
}
