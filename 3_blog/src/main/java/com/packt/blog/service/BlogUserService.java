package com.packt.blog.service;

import org.jooq.DSLContext;
import org.jooq.Result;

import static org.jooq.impl.DSL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.blog.mapper.BlogUserMapper;
import com.packt.blog.model.BlogUser;

@Service
public class BlogUserService {

	@Autowired private BlogUserMapper blogUserMapper;

	public void addNewUser(BlogUser user) {
		blogUserMapper.addNewUser(user);
	}
	
}
