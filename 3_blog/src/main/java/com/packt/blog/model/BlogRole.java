package com.packt.blog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class BlogRole {

	public BlogRole() {}
	public BlogRole(String roleName) {
		this.roleName = roleName;
	}
	
	@Id
	private String roleName;
}
