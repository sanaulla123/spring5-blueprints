package com.packt.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.packt.dal.mappers.CountryRowMapper;
import com.packt.model.Country;

@Service
public class CountryDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	
	public List<Country> getCountries(){
		return jdbcTemplate.query("SELECT "
				+ "  	c.Code, "
				+ "		c.Name, "
				+ "		c.Continent, "
				+ "		c.region, "
				+ "		c.SurfaceArea, "
				+ "		c.IndepYear, "
				+ "		c.Population, "
				+ "		c.LifeExpectancy, "
				+ "		c.GNP, "
				+ "		c.LocalName, "
				+ "		c.GovernmentForm, "
				+ "		c.HeadOfState, "
				+ "		c.code2 "
				+ " FROM country c"
				+ " ORDER BY c.code DESC", new CountryRowMapper());
	}
	
}
