package com.packt.dal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packt.config.TestDBConfiguration;
import com.packt.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig( classes = {TestDBConfiguration.class, CountryDAO.class})
public class CountryDAOTest {

	@Autowired CountryDAO countryDao;
	
	@Test
	public void testGetCountries() {
		List<Country> countries = countryDao.getCountries();
		assertTrue(countries.size() == 239);
		
		Country c = countries.get(0);
		assertNotNull(c.getCode());
		assertNotNull(c.getName());
		assertNotNull(c.getContinent());
	}
}
