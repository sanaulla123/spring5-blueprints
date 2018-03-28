package com.packt.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.blog.model.BlogUser;

@JooqTest
@SpringJUnitConfig(classes = {BlogUserService.class})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class BlogUserServiceTest {

	@Autowired BlogUserService blogUserService;
	
	@Test
	public void test_addNewUser() {
		BlogUser user = new BlogUser("test", "12345", "test@test.com", "Test User");
		boolean result = blogUserService.addNewUser(user);
		assertThat(result).isTrue();
	}
}
