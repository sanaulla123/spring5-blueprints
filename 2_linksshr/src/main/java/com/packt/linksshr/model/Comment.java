package com.packt.linksshr.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Comment {

	private String id;
	private String linkId;
	private String content;
	private String postedBy;
	private Date postedOn;
	private String parentId;
	private Date updatedOn;
	
}
