package com.packt.blog.model;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import lombok.Data;

@Data
public class BlogPost implements Mappable{
	
	private String id;
	private String title;
	private String slug;
	private String content;
	private String status;
	private List<String> categories = new ArrayList<>();
	private List<String> tags = new ArrayList<>();
	private Integer allowComment = 1;
	private Integer commentCount = 0;
	
	private LocalDateTime publishedOn;
	private String publishedBy;
	private BlogUser publishedByUser;
	
	private LocalDateTime updatedOn;
	private String updatedBy;
	private BlogUser updatedByUser;
	
	@Override
	public Field getIdField() throws Exception{
		return this.getClass().getDeclaredField("id");
	}
	
}
