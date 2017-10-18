package com.packt.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.packt.dal.mapper.CountryRowMapper;
import com.packt.model.Country;

@Service
public class CountryDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	private static final String SELECT_CLAUSE = "SELECT "
			+ "  	c.Code, "
			+ "		c.Name, "
			+ "		c.Continent, "
			+ "		c.region, "
			+ "		c.SurfaceArea surface_area, "
			+ "		c.IndepYear indep_year, "
			+ "		c.Population, "
			+ "		c.LifeExpectancy life_expectancy, "
			+ "		c.GNP, "
			+ "		c.LocalName local_name, "
			+ "		c.GovernmentForm government_form, "
			+ "		c.HeadOfState head_of_state, "
			+ "		c.code2 ,"
			+ "		c.capital ,"
			+ "		cy.name capital_name "
			+ " FROM country c"
			+ " LEFT OUTER JOIN city cy ON cy.id = c.capital ";
	
	private static final String SEARCH_WHERE_CLAUSE = " AND ( LOWER(c.name) "
			+ "	LIKE '%'||LOWER(:search)||'%' "
			+ "	OR LOWER(c.localname) "
			+ "	LIKE  '%'||LOWER(:search)||'%' )";
	
	private static final String CONTINENT_WHERE_CLAUSE = 
			" AND c.continent = :continent ";
	private static final String REGION_WHERE_CLAUSE = 
			" AND c.region = :region ";
	
	public List<Country> getCountries(Map<String, String> params){
		return namedParamJdbcTemplate.query(SELECT_CLAUSE
				+ " WHERE 1 = 1 "
				+ (StringUtils.isNotEmpty(params.get("search")) ? SEARCH_WHERE_CLAUSE : "")
				+ (StringUtils.isNotEmpty(params.get("continent")) ? CONTINENT_WHERE_CLAUSE : "")
				+ (StringUtils.isNotEmpty(params.get("region")) ? REGION_WHERE_CLAUSE : "")
				+ " ORDER BY c.code ",
				params, new CountryRowMapper());
	}

	public Country getCountryDetail(String code) {
		return jdbcTemplate.queryForObject(SELECT_CLAUSE 
				+"	WHERE c.code = ?", new Object[] { code }, 
				new CountryRowMapper());
	}
	
	public void editCountryDetail(String code, Country country) {
		//namedParamJdbcTemplate.update
	}
	
}
