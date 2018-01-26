package com.packt.blog.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class BlogUser implements Mappable{
	
	
	public BlogUser() {}
	public BlogUser(String username, String password, String email, String name, String... roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.roles = Arrays.asList(roles);
	}
	
	 String username;
	 String password;
	 String email;
	 String name;
	 Integer enabled = 1;
	 List<String> roles;
	
	public void addRole(String role) {
		if ( roles == null ) {
			roles = new ArrayList<>();
		}
		roles.add(role);
	}
	
	@Override
	public Field getIdField() throws Exception {
		return this.getClass().getDeclaredField("username");
	}
	
}
