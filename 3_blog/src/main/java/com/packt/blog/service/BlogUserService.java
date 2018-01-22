package com.packt.blog.service;

import org.springframework.stereotype.Service;

import com.packt.blog.model.BlogUser;

@Service
public class BlogUserService {

	public BlogUser getUser(String username) {
		return new BlogUser(username, "12345", username, username);
	}
}
