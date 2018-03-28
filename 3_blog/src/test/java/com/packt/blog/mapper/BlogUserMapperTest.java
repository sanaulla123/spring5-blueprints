package com.packt.blog.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.blog.BlogAppRoles;
import com.packt.blog.model.BlogRole;
import com.packt.blog.model.BlogUser;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@MybatisTest
@ActiveProfiles("test")
public class BlogUserMapperTest {

	@Autowired private BlogUserMapper blogUserMapper;
	
	@Test
	public void test_addNewUser() {
		BlogUser user = new BlogUser("test", "12345", "test@test.com", "Test User", 
				BlogAppRoles.author.toString());
		blogUserMapper.addNewUser(user);
		
		BlogUser userFromDb = blogUserMapper.getUser("test");
		assertThat(userFromDb.getUsername()).isEqualTo(user.getUsername());
		assertThat(userFromDb.getPassword()).isEqualTo(user.getPassword());
		assertThat(userFromDb.getEmail()).isEqualTo(user.getEmail());
		assertThat(userFromDb.getRoles()).containsExactlyInAnyOrder(
				new BlogRole(BlogAppRoles.author.toString()));
	}
	
	@Test public void test_updateUser() {
		
	}
	
	@Test public void test_updatePassword() {
		
	}
	
	@Test public void test_updateEnabled() {
		
	}
}
