package com.packt.linksshr.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
import com.packt.linksshr.model.Comment;
import com.packt.linksshr.model.Link;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = {AppConfiguration.class, TestAppConfiguration.class})
public class CommentServiceTest {

	@Autowired CommentService commentService;
	@Autowired LinkService linkService;
	
	@Qualifier("testMongoTemplate")
	@Autowired ReactiveMongoTemplate mongoTemplate;
	
	private List<String> commentIds = new ArrayList<>();
	
	@Before
	public void setup() {
		commentService.setMongoTemplate(mongoTemplate);
		linkService.setMongoTemplate(mongoTemplate);
	}
	
	@After
	public void cleanup() {
		commentIds.stream().forEach( id -> {
			commentService.deleteComment(id).block();
		});
	}
	
	@Test public void test_newComment() {
		Comment c = new Comment();
		c.setContent("Sample");
		String id = commentService.newComment(c).block().getId();
		commentIds.add(id);
		Comment cFromDb = commentService.getComment(id).block();
		assertThat(c.getContent()).isEqualTo(cFromDb.getContent());
	}
	
	@Test public void test_editComment() {
		Comment c = new Comment();
		c.setContent("Sample");
		String id = commentService.newComment(c).block().getId();
		commentIds.add(id);
		c.setContent("updated sample content");
		c.setId(id);
		commentService.editComment(c).block();
		
		Comment cFromDb = commentService.getComment(id).block();
		assertThat(c.getContent()).isEqualTo(cFromDb.getContent());
	}
	
	@Test public void test_getComments() {
		Link l = new Link();
		String linkId = linkService.newLink(l).block().getId();
		Comment c = new Comment();
		c.setContent("sample content");
		c.setLinkId(linkId);
		commentIds.add(commentService.newComment(c).block().getId());
		c = new Comment();
		c.setContent("sample other content");
		c.setLinkId(linkId);
		commentIds.add(commentService.newComment(c).block().getId());
		
		List<Comment> commentsForLink = commentService.getComments(linkId)
				.collectList().block();
		assertThat(commentsForLink).hasSize(2);
	}
	
	
}
