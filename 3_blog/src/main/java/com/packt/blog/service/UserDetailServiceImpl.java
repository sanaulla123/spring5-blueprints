package com.packt.blog.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packt.blog.model.BlogUser;
import com.packt.blog.model.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired BlogUserEsService blogUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BlogUser blogUser;
		try {
			blogUser = blogUserService.getUser(username);
			UserPrincipal principal = new UserPrincipal(blogUser);
			return principal;
		} catch (Exception e) {
			log.error("Error occurred while getting user from database", e);
			throw new UsernameNotFoundException("User not found");
		}
		
	}

}
