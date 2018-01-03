package com.packt.linksshr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.QueryBuilder;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
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
	
	public Mono<Link> editLink(Link link) {
		return mongoTemplate.save(link);
	}
	
	public Mono<Link> getLinkDetail(String id){
		return mongoTemplate.findById(id, Link.class);
	}
	
	public Mono<DeleteResult> deleteLink(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.remove(query, Link.class);
	}
	
	
	public Mono<UpdateResult> addUpVote(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		Update update = new Update();
		update.inc("upVoteCount", 1);
		return mongoTemplate.updateFirst(query, update, Link.class);
	}
	
	public Mono<UpdateResult> addDownVote(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		Update update = new Update();
		update.inc("downVoteCount", 1);
		return mongoTemplate.updateFirst(query, update, Link.class);
	}
}
