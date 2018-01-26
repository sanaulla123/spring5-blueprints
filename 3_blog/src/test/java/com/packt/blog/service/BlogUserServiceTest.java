package com.packt.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.blog.BlogAppRoles;
import com.packt.blog.model.BlogUser;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class BlogUserServiceTest {

	@Autowired BlogUserService blogUserService;
	
	@Test public void test_getAsMap() throws Exception {
		BlogUser user = new BlogUser("sanaulla", "12345", "sanaulla123@gmail.com", "Sanaulla", 
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString());
		Map<String, Object> map = user.getAsMap();
		assertThat(map).isNotEmpty();
	}
	
	@Test public void test_getUser() throws Exception {
		BlogUser user = new BlogUser("sanaulla", "12345", "sanaulla123@gmail.com", "Sanaulla", 
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString());
		blogUserService.addNewUser(user);
		
		BlogUser userFromEs = blogUserService.getUser("sanaulla");
		assertThat(userFromEs.getUsername()).isEqualTo("sanaulla");
		assertThat(userFromEs.getRoles()).hasSameElementsAs(Arrays.asList(
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString()) );
		
		blogUserService.deleteUser("sanaulla");
	}
	
	@Test public void test_addRole() throws Exception{
		BlogUser user = new BlogUser("sanaulla", "12345", "sanaulla123@gmail.com", "Sanaulla", 
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString());
		blogUserService.addNewUser(user);
		blogUserService.addRole("sanaulla", BlogAppRoles.role_user.toString());
		
		BlogUser userFromEs = blogUserService.getUser("sanaulla");
		assertThat(userFromEs.getRoles()).hasSameElementsAs(Arrays.asList(
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString(), 
				BlogAppRoles.role_user.toString()) );
		
		blogUserService.deleteUser("sanaulla");
		
	}
	
	@Test public void test_editUser() throws Exception{
		BlogUser user = new BlogUser("sanaulla", "12345", "sanaulla123@gmail.com", "Sanaulla", 
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString());
		blogUserService.addNewUser(user);
		String name = "Mohamed Sanaulla";
		user.setName(name);
		
		blogUserService.editUser(user);
		
		BlogUser userFromEs = blogUserService.getUser("sanaulla");
		assertThat(userFromEs.getName()).isEqualTo(name);
		blogUserService.deleteUser("sanaulla");
	}
}
