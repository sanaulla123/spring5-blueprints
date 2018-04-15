package com.packt.blog.model;

import lombok.Data;

@Data
public class BlogRole {

	public BlogRole() {}
	public BlogRole(String roleName) {
		this.roleName = roleName;
	}
	
	private String roleName;
}
