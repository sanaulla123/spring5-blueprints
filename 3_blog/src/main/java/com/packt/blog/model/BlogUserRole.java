package com.packt.blog.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogUserRole implements Serializable {
	
	private String username;
	
	private String roleName;
	
	
	public BlogUserRole() {}
	
	public BlogUserRole(String username, String roleName) {
		this.username = username;
		this.roleName = roleName;
	}
	
}
