package com.packt.blog.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.blog.model.BlogUser;

@Service
public class BlogUserService {

	private static final String index = "user";
	private static final String type  = "user";
	
	@Autowired RestHighLevelClient elasticClient;
	
	public BlogUser getUser(String username) throws IOException {
		GetRequest request = new GetRequest(index, type, username);
		return BlogUser.fromMap(elasticClient.get(request).getSource());
	}
	
	public IndexResponse addNewUser(BlogUser user) throws IOException {
		IndexRequest request = new IndexRequest(index, type, user.getUsername())
				.source(user.getAsMap());
		IndexResponse response = elasticClient.index(request);
		return response;
	}
	
	public void addRole(String username, String role) throws IOException {
		Map<String, Object> params = Collections.singletonMap("role", role);
		Script script = new Script(ScriptType.INLINE, "painless", "ctx._source.roles += params.role", params);
		UpdateRequest request = new UpdateRequest(index, type, username)
				.script(script);
		elasticClient.update(request);
	}
	
	public DeleteResponse deleteUser(String username) throws IOException {
		DeleteRequest request = new DeleteRequest(index, type, username);
		return elasticClient.delete(request);
	}
}
