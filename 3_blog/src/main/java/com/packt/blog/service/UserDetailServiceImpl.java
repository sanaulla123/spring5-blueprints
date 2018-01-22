package com.packt.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packt.blog.model.BlogUser;
import com.packt.blog.model.UserPrincipal;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired BlogUserService blogUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BlogUser blogUser = blogUserService.getUser(username);
		UserPrincipal principal = new UserPrincipal(blogUser);
		return principal;
	}

}
