package com.packt.blog.model;

import java.io.IOException;
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
public class BlogUser {
	
	
	public BlogUser() {}
	public BlogUser(String username, String password, String email, String name, String... roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.roles = Arrays.asList(roles);
	}
	
	private String username;
	private String password;
	private String email;
	private String name;
	private Integer enabled = 1;
	private List<String> roles;
	
	public void addRole(String role) {
		if ( roles == null ) {
			roles = new ArrayList<>();
		}
		roles.add(role);
	}
	
	public static BlogUser fromMap(Map<String, Object> map) {
		BlogUser user = new BlogUser(MapUtils.getString(map, "username"), 
				MapUtils.getString(map, "password"),
				MapUtils.getString(map, "email"), 
				MapUtils.getString(map, "name"));
		user.setRoles((List<String>)MapUtils.getObject(map, "roles"));
		return user;
	}
	
	public Map<String, Object> getAsMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		map.put("email", email);
		map.put("name", name);
		map.put("enabled", enabled);
		map.put("roles", roles);
		return map;
	}
}
