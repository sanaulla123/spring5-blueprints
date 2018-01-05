package com.packt.linksshr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User{
	@Id
	private String username;
	private String email;
	private String name;
	private String password;
}
