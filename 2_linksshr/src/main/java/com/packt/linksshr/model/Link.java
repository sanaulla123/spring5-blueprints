package com.packt.linksshr.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Link {
	String id;
	String url;
	String title;
	Integer upVoteCount = 0;
	Integer downVoteCount = 0;
	public Integer getVoteCount() {
		return upVoteCount - downVoteCount;
	}
	
	String category;
	List<String> tags = new ArrayList<>();
	String postedBy;
}
