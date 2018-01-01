package com.packt.linksshr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories
@ComponentScan(basePackages = "com.packt.linksshr")
public class AppConfiguration {

	
}
