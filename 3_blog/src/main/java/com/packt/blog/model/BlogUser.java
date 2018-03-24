package com.packt.blog.model;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
@Entity
public class BlogUser implements Mappable, Serializable {

	public BlogUser() {
	}

	public BlogUser(String username, String password, String email, String name, String... roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.roles = Arrays.asList(roles).stream()
				.map(role -> new BlogUserRole(username, role))
				.collect(Collectors.toList());
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	String username;
	
	String password;
	String email;
	String name;
	Integer enabled = 1;
	
	@OneToMany(mappedBy = "username", cascade = CascadeType.ALL)
	List<BlogUserRole> roles;

	public void addRole(String aRole) {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		roles.add(new BlogUserRole(this.username, aRole));
	}

	@Override
	public Field getIdField() throws Exception {
		return this.getClass().getDeclaredField("username");
	}

}
