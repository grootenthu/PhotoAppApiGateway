package com.photoapp.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

/**
 * 
 * A configuration filter class that supports both pre & post global filter
 * 
 * @author groot
 *
 */
@Configuration
public class GlobalFiltersConfiguration {

	final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

	@Bean
	public GlobalFilter secondGlobalFilter() {

		return (exchange, chain) -> {
			logger.info("Second Global Pre Filter Executed");

			return chain.filter(exchange).and(Mono.fromRunnable(() -> {
				logger.info("Second Global Post Filter Executed");
			}));
		};
	}

	@Bean
	public GlobalFilter thirdGlobalFilter() {

		return (exchange, chain) -> {
			logger.info("Third Global Pre Filter Executed");

			return chain.filter(exchange).and(Mono.fromRunnable(() -> {
				logger.info("Third Global Post Filter Executed");
			}));
		};
	}

}
