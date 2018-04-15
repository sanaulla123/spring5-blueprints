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

import java.util.HashMap;
import java.util.Map;

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
		assertThat(userFromDb.getRoleName()).isEqualTo(BlogAppRoles.author.toString());
	}
	
	@Test public void test_updateUser() {
		BlogUser user = new BlogUser("test", "12345", "test@test.com", "Test User", 
				BlogAppRoles.author.toString());
		blogUserMapper.addNewUser(user);
		
		user.setEmail("123@test.com");
		user.setName("Test User Change");
		user.setUpdatedBy("test");
		blogUserMapper.updateUser(user);
		
		BlogUser userFromDb = blogUserMapper.getUser("test");
		assertThat(userFromDb.getEmail()).isEqualTo(user.getEmail());
		assertThat(userFromDb.getName()).isEqualTo(user.getName());
	}
	
	@Test public void test_updatePassword() {
		BlogUser user = new BlogUser("test", "12345", "test@test.com", "Test User", 
				BlogAppRoles.author.toString());
		blogUserMapper.addNewUser(user);
		user.setPassword("56789");
		Map<String, Object> params = new HashMap<>();
		params.put("password", user.getPassword());
		params.put("updatedBY", user.getUsername());
		params.put("username", user.getUsername());
		blogUserMapper.updatePassword(params);
		
		BlogUser userFromDb = blogUserMapper.getUser(user.getUsername());
		assertThat(userFromDb.getPassword()).isEqualTo(user.getPassword());
	}
	
	@Test public void test_updateEnabled() {
		BlogUser user = new BlogUser("test", "12345", "test@test.com", "Test User", 
				BlogAppRoles.author.toString());
		blogUserMapper.addNewUser(user);
		Map<String, Object> params = new HashMap<>();
		params.put("enabled", 0);
		params.put("updatedBY", user.getUsername());
		params.put("username", user.getUsername());
		blogUserMapper.updateEnabled(params);
		
		BlogUser userFromDb = blogUserMapper.getUser(user.getUsername());
		assertThat(userFromDb.getEnabled()).isEqualTo(0);
	}
	
	@Test public void test_updateRole() {
		BlogUser user = new BlogUser("test", "12345", "test@test.com", "Test User", 
				BlogAppRoles.author.toString());
		blogUserMapper.addNewUser(user);
		Map<String, Object> params = new HashMap<>();
		params.put("roleName", BlogAppRoles.admin.toString());
		params.put("updatedBY", user.getUsername());
		params.put("username", user.getUsername());
		blogUserMapper.updateRole(params);
		
		BlogUser userFromDb = blogUserMapper.getUser(user.getUsername());
		assertThat(userFromDb.getRoleName()).isEqualTo(BlogAppRoles.admin.toString());
	}
}
