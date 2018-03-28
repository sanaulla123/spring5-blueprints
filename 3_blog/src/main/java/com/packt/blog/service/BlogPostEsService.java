package com.packt.blog.service;

import java.io.IOException;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.packt.blog.model.BlogPost;

public class BlogPostEsService {

	@Autowired RestHighLevelClient esClient;
	private static final String index = "blog";
	private static final String type = "blog";
	
	public void addBlogPost(BlogPost post) throws Exception{
		IndexRequest request = new IndexRequest(index, type)
				.source(post.getAsMap());
		esClient.index(request);
	}
	
	public void editBlogPost(BlogPost post) throws Exception {
		UpdateRequest request = new UpdateRequest(index, type, post.getId())
				.doc(post.getAsMap());
		esClient.update(request);
	}
	
	public void deleteBlogPost(String id) throws Exception {
		DeleteRequest request = new DeleteRequest(index, type, id);
		esClient.delete(request);
	}
	
	public void updateStatus(String id, String newStatus) throws Exception{
		UpdateRequest request = new UpdateRequest(index, type, id)
				.doc("status", newStatus);
		esClient.update(request);
		
	}
	
	//public BlogPost
}
