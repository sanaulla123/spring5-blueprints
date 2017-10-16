package com.packt.dal.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.model.Country;

public class CountryRowMapper implements RowMapper<Country>{

	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		Country c = new Country();
		c.setCode(rs.getString("code"));
		c.setName(rs.getString("name"));
		c.setContinent(rs.getString("continent"));
		return c;
	}
	

}
