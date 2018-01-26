package com.packt.blog.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Subscriber {
	
	public Subscriber(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	String name;
	String email;
	LocalDateTime subscribedOn;
	
	public Map<String, Object> getAsMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		//map
		return map;
	}
}
