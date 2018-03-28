package com.packt.blog.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.blog.enums.BlogPostStatus;
import com.packt.blog.model.BlogPost;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class BlogPostEsServiceTest {
	@Autowired BlogPostEsService blogPostService;
	
	@Test public void test_addBlogPost() throws Exception{
		BlogPost post = buildNewBlogPost();
		blogPostService.addBlogPost(post);
		
		
		//blogPost.set
	}
	
	@Test public void test_editBlogPost() throws Exception {
		
	}
	
	@Test public void test_updateStatus() throws Exception{
		
	}
	
	private BlogPost buildNewBlogPost() {
		BlogPost blogPost = new BlogPost();
		blogPost.setTitle("BLog post title");
		blogPost.setSlug("blog_post_title");
		blogPost.setContent("## Some content in markdown");
		blogPost.setStatus(BlogPostStatus.Pending.toString());
		blogPost.setCategories(Arrays.asList("Java", "Scala"));
		blogPost.setTags(Arrays.asList("java", "java 9", "scala"));
		blogPost.setPublishedBy("sanaulla");
		blogPost.setPublishedOn(LocalDateTime.now());
		return blogPost;
	}
}
