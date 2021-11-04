package com.photoapp.gateway.filter;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalGatewayPreFilter implements GlobalFilter {

	final Logger logger = LoggerFactory.getLogger(GlobalGatewayPreFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		logger.info("Global Gateway Pre Filter executed....");

		String requestPath = exchange.getRequest().getPath().toString();
		logger.info("Request Path : {}", requestPath);

		HttpHeaders httpHeaders = exchange.getRequest().getHeaders();

		Set<String> headerNames = httpHeaders.keySet();

		headerNames.forEach(e -> {
			String headerValue = httpHeaders.getFirst(e);
			logger.info(e + " " + headerValue);
		});

		return chain.filter(exchange);
	}

}
