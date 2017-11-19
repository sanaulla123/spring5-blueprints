package com.packt.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class LookupDAO {

	@Autowired 
	NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	public List<String> getContinents(){
		MapSqlParameterSource params = new MapSqlParameterSource();
		return namedParamJdbcTemplate.queryForList("SELECT DISTINCT continent FROM country "
				+ " ORDER BY continent", 
				params, String.class);
	}
	
	public List<String> getRegions(){
		MapSqlParameterSource params = new MapSqlParameterSource();
		return namedParamJdbcTemplate.queryForList("SELECT DISTINCT region FROM country "
				+ " ORDER BY region", 
				params, String.class);
	}
}
