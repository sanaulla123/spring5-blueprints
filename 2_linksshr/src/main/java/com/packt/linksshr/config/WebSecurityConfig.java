package com.packt.linksshr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig{
	
	
	@Bean
	SecurityWebFilterChain security(ServerHttpSecurity httpSecurity) {
		
		return httpSecurity.authorizeExchange()
				.pathMatchers(HttpMethod.GET, "/", "/message").permitAll()
				.anyExchange().authenticated()
				.and()
				.build();
	}
}
