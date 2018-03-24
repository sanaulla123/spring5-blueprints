package com.packt.blog.repos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.blog.BlogAppRoles;
import com.packt.blog.model.BlogUser;
import com.packt.blog.model.BlogUserRole;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class BlogUserRepositoryTest {

	@Autowired BlogUserRepository blogUserRepos;
	
	@Test
	public void test_getUserByUsername() {
		String username = "admin";
		BlogUser user = blogUserRepos.getOne(username);
		assertThat(user.getUsername()).isEqualTo(username);
		assertThat(user.getEmail()).isEqualTo("admin@gmail.com");
		assertThat(user.getPassword()).isEqualTo("12345");
		assertThat(user.getName()).isEqualTo("Admin User");
		assertThat(user.getRoles()).hasSize(1);
		assertThat(user.getRoles()).containsExactly(new BlogUserRole(username, "admin"));
	}
	
	@Test
	public void test_addUser() {
		BlogUser user = new BlogUser("test", "12345", "test@test.com", "Test User", BlogAppRoles.user.toString());
		blogUserRepos.save(user);
		
		BlogUser userFromDb = blogUserRepos.getOne("test"); 
		assertThat(userFromDb).isEqualTo(user);
	}
	
	@Test
	public void test_editUser() {
		String username = "admin";
		BlogUser user = blogUserRepos.getOne(username);
		user.setName("Admin Name Changed");
		blogUserRepos.save(user);
		
		BlogUser userFromDb = blogUserRepos.getOne("admin");
		assertThat(userFromDb.getName()).isEqualTo("Admin Name Changed");
	}
}
