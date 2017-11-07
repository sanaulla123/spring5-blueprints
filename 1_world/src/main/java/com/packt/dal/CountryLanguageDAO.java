package com.packt.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.packt.dal.mapper.CountryLanguageRowMapper;
import com.packt.model.CountryLanguage;

import lombok.Setter;

@Service
@Setter
public class CountryLanguageDAO {
	@Autowired NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	public List<CountryLanguage> getLanguages(String countryCode){
		Map<String, Object> params = new HashMap<>();
		params.put("code", countryCode);
		
		return namedParamJdbcTemplate.query("SELECT * FROM countrylanguage"
				+ " WHERE countrycode = :code", params, new CountryLanguageRowMapper());
	}
	
	public void addLanguage(String countryCode, CountryLanguage cl) {
		namedParamJdbcTemplate.update("INSERT INTO countrylanguage ( "
				+ " countrycode, language, isofficial, percentage ) "
				+ " VALUES ( :country_code, :language, "
				+ " :is_official, :percentage ) ", 
				getAsMap(countryCode, cl));
	}
	
	public void deleteLanguage (String countryCode, String language ) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", countryCode);
		params.put("lang", language);
		namedParamJdbcTemplate.update("DELETE FROM countrylanguage "
				+ " WHERE countrycode = :code AND "
				+ " language = :lang ", params);
	}
	
	private  Map<String, Object> getAsMap(String countryCode, CountryLanguage cl){
		Map<String, Object> map = new HashMap<>();
		map.put("country_code", countryCode);
		map.put("language", cl.getLanguage());
		map.put("is_official", cl.getIsOfficial());
		map.put("percentage", cl.getPercentage());
		return map;
	}
}
