package com.packt.linksshr.service;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.linksshr.config.AppConfiguration;
import com.packt.linksshr.config.TestAppConfiguration;
import com.packt.linksshr.model.Link;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = {AppConfiguration.class, TestAppConfiguration.class})
public class LinkServiceTest {
	@Autowired LinkService linkService;
	
	@Qualifier("testMongoTemplate")
	@Autowired ReactiveMongoTemplate mongoTemplate;
	
	@Before
	public void setup() {
		linkService.setMongoTemplate(mongoTemplate);
	}
	
	@Test
	public void test_newLink() throws InterruptedException {
	    CountDownLatch latch = new CountDownLatch(1);
		Link l = new Link();
		l.setCategory("category");
		l.setTitle("dff");
		l.setUrl("sss");
		linkService.newLink(l)
			.doAfterTerminate(latch::countDown)
			.subscribe();
		latch.await();
		
	}
}
