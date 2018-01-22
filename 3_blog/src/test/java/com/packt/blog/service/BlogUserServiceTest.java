package com.packt.blog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import com.packt.blog.model.BlogUser;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class BlogUserServiceTest {

	@Autowired BlogUserService blogUserService;
	
	@Test public void test_getUser() {
		String username = "sanaulla";
		
		BlogUser user = blogUserService.getUser(username);
		assertThat(user.getUsername()).isEqualTo(username);
	}
}
