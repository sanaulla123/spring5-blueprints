package com.packt.blog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;

import com.packt.blog.BlogAppRoles;
import com.packt.blog.model.BlogUser;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class BlogUserServiceTest {

	@Autowired BlogUserService blogUserService;
	
	@Test public void test_getUser() throws IOException {
		BlogUser user = new BlogUser("sanaulla", "12345", "sanaulla123@gmail.com", "Sanaulla", 
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString());
		blogUserService.addNewUser(user);
		
		BlogUser userFromEs = blogUserService.getUser("sanaulla");
		assertThat(userFromEs.getUsername()).isEqualTo("sanaulla");
		assertThat(userFromEs.getRoles()).hasSameElementsAs(Arrays.asList(
				BlogAppRoles.role_admin.toString(), BlogAppRoles.role_author.toString()) );
		
		blogUserService.deleteUser("sanaulla");
	}
	
	@Test public void test_addRole() throws IOException{
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
}
