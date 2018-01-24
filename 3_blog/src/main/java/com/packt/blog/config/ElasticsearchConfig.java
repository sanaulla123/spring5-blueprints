package com.packt.blog.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = {"com.packt.blog.repos"})
@Configuration
public class ElasticsearchConfig {
	
	@Value("${elasticsearch.host}")
	private String host;
	
	@Value("${elasticsearch.port}")
	private int port;
	
	@Bean
	public RestHighLevelClient restClient() {
		RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder( new HttpHost(host, port, "http") ) );
		return client;
	}
}
