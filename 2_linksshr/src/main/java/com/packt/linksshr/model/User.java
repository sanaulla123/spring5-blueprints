package com.packt.linksshr.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {
	String id;
	String email;
}
