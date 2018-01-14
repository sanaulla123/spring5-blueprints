package com.packt.linksshr.handler;


import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.packt.linksshr.model.Comment;
import com.packt.linksshr.model.Link;
import com.packt.linksshr.service.CommentService;
import com.packt.linksshr.service.LinkService;

import reactor.core.publisher.Mono;

@Configuration
public class HttpRequestHandlers {
	@Autowired LinkService linkService;
	@Autowired CommentService commentService;
	
	Mono<ServerResponse> message(ServerRequest request) {
		System.out.println(request.principal().block().getName());
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just("Hello World"), String.class);
	}
	
	Mono<ServerResponse> getLinks(ServerRequest request) {
		Map<String, String> params = request.queryParams().toSingleValueMap();
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(linkService.getLinks(params), Link.class);
	}
	
	Mono<ServerResponse> newLink(ServerRequest request) {
		Link link = request.bodyToMono(Link.class).block();
		System.out.println(link);
		link.setPostedBy(request.principal().block().getName());
		link.setPostedOn(LocalDateTime.now());
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(linkService.newLink(link), Link.class);
	}
	
	Mono<ServerResponse> newComment(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(request.bodyToMono(Comment.class)
						.doOnSuccess(c -> {
							c.setPostedBy(request.principal().block().getName());
							c.setPostedOn(LocalDateTime.now());
							commentService.newComment(c);
						}), Comment.class);
	}
	
	@Bean
	RouterFunction<?> routerFunctions() {
		return RouterFunctions.route(RequestPredicates.GET("/message"), this::message)
			.andRoute(RequestPredicates.GET("/api/links"), this::getLinks)
			.andRoute(RequestPredicates.POST("/api/links"), this::newLink)
			.andRoute(RequestPredicates.POST("/api/comments"), this::newComment)
			;
	}
}
