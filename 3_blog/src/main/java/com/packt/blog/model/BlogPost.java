package com.packt.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BlogPost {
	private String id;
	private String title;
	private String slug;
	private String content;
	private String status;
	private List<String> categories;
	private List<String> tags;
	private Integer allowComment = 1;
	private Integer commentCount = 0;
	
	private LocalDateTime publishedOn;
	private String publishedBy;
	private BlogUser publishedByUser;
	
	private LocalDateTime updatedOn;
	private String updatedBy;
	private BlogUser updatedByUser;
}
