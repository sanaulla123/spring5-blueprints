package com.packt.blog.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BlogUser implements Mappable, Serializable {

	public BlogUser() {
	}

	public BlogUser(String username, String password, String email, String name, String role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.roleName = role;
	}

	String username;
	
	String password;
	String email;
	String name;
	Integer enabled = 1;
	LocalDateTime createdOn;
	String createdBy;
	LocalDateTime updatedOn;
	String updatedBy;
	String roleName;

	@Override
	public Field getIdField() throws Exception {
		return this.getClass().getDeclaredField("username");
	}
	
}
