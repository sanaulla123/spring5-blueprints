package com.packt.linksshr.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.packt.linksshr.model.Link;

public interface LinkRepository extends ReactiveMongoRepository<Link, Long> {
}
