package com.packt.linksshr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.packt.linksshr.model.Link;

import reactor.core.publisher.Mono;

@Service
public class LinkService {

	@Autowired ReactiveMongoTemplate mongoTemplate;
	
	public void setMongoTemplate(ReactiveMongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public Mono<Link> newLink(Link link) {
		return mongoTemplate.insert(link);
	}
}
