package com.packt.blog.service;

import org.jooq.DSLContext;
import org.jooq.Result;

import static org.jooq.impl.DSL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.blog.model.BlogUser;

@Service
public class BlogUserService {

	@Autowired private DSLContext create;

	public boolean addNewUser(BlogUser user) {
		int rows = create.insertInto(table("blog_user"))
			.set(field("username"), user.getUsername())
			.set(field("email"), user.getEmail())
			.set(field("password"), user.getPassword())
			.set(field("name"), user.getName())
			.set(field("enabled"), user.getEnabled())
			.execute();
		return rows == 1;
	}
	
	public void getUserDetail(String username) {
		create.select(field("u.username"), field("u.email"), 
				field("u.password"), field("u.name"), 
				field("u.enabled"), field("u.role_name"))
			.from(table("blog_user").as("u"))
			.leftOuterJoin(table("blog_user_role").as("ur"))
				.on(field("ur.username").eq(field("u.username")))
			.where(field("u.username").eq(username))
			.fetch();
			
	}
}
