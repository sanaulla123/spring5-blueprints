package com.packt.blog.model;

import java.util.List;

import lombok.Data;

@Data
public class BlogUser {
	public BlogUser() {}
	public BlogUser(String username, String password, String email, String name) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
	}
	
	private String username;
	private String password;
	private String email;
	private String name;
	private Integer enabled = 1;
	private List<Role> roles;
}
