package com.packt.blog.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.packt.blog.model.BlogUser;

@Mapper
public interface BlogUserMapper {

	public void addNewUser(BlogUser user);
	public BlogUser getUser(String username);
	public void updatePassword(Map<String, Object> params);
	public void updateEnabled(Map<String, Object> params);
	public void updateUser(BlogUser user);
}
