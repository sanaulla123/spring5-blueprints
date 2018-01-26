package com.packt.blog.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface Mappable {
	
	Field getIdField() throws Exception;
	
	default Map<String, Object> getAsMap() throws Exception {
		Map<String, Object> map = new HashMap<>();
		for ( Field f : this.getClass().getDeclaredFields()) {
			System.out.println(f.getName() +" = " + f.get(this));
			map.put(f.getName(), f.get(this));
		}
		
		return map;
	}
	
	default Mappable fromMap(Map<String, Object> map) throws Exception{
		for ( Field f : this.getClass().getDeclaredFields()) {
			if ( map.containsKey(f.getName())) {
				f.set(this, map.get(f.getName()));
			}
		}
		//getIdField().set(this, map.get("_id"));
		return this;
	}
	
	
}
