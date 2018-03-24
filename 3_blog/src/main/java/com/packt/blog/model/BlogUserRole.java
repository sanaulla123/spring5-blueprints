package com.packt.blog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BlogUserRole implements Serializable {
	
	@Id
	private String username;
	@Id
	private String roleName;
	
	public BlogUserRole() {}
	
	public BlogUserRole(String username, String roleName) {
		this.username = username;
		this.roleName = roleName;
	}
}
