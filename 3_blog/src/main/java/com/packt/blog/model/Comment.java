package com.packt.blog.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Comment {
	private String text;
	
	private String status;
	private LocalDateTime postedOn;
	
	//This will be for non-logged in users
	private String postedByName;
	private String postedByEmail;
	
	//we will not ask for name and email for logged in users
	private String postedBy;
	BlogUser postedByUser;
}
