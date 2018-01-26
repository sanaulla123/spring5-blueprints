package com.packt.blog.service;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.blog.model.Subscriber;

@Service
public class SubscriberService {

	@Autowired RestHighLevelClient esClient;
	private static final String index = "subscriber";
	private static final String type = "subscriber";
	
	public void registerSubscriber(String name, String email) throws IOException {
		Subscriber subscriber = new Subscriber(name, email);
		IndexRequest request = new IndexRequest(index, type, email)
				.source(subscriber.getAsMap());
		esClient.index(request);
	}
}
