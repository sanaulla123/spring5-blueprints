package com.packt.linksshr.handler;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class HttpRequestHandlers {

	@Bean
	RouterFunction<?> routerFunctions() {
		return RouterFunctions.route(RequestPredicates.GET("/message"),request -> {
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just("Hello World"), String.class);
		});
	}
}
