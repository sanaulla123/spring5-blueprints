package com.packt.dal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Country> countries = countryDao.getCountries(Collections.EMPTY_MAP);
		assertTrue(countries.size() == 239);
		
		Country c = countries.get(0);
		assertThat(c.toString()).isEqualTo("Country(code=ABW, name=Aruba, "
				+ "continent=North America, region=Caribbean, surfaceArea=193.0, "
				+ "indepYear=0, population=103000, lifeExpectancy=78.4000015258789, "
				+ "gnp=828.0, localName=Aruba, governmentForm=Nonmetropolitan Territory of The Netherlands, "
				+ "headOfState=Beatrix, capital=City(id=129, name=Oranjestad, countryCode=null, "
				+ "country=null, district=null, population=null), code2=AW)");
	}
	
	@Test
	public void testGetCountries_searchByName() {
		Map<String, String> params = new HashMap<>();
		params.put("search", "Aruba");
		List<Country> countries = countryDao.getCountries(params);
		assertThat(countries).hasSize(1);
	}
	
	@Test
	public void testGetCountries_searchByLocalName() {
		Map<String, String> params = new HashMap<>();
		params.put("search", "Bharat/India");
		List<Country> countries = countryDao.getCountries(params);
		assertThat(countries).hasSize(1);
	}
	
	@Test
	public void testGetCountries_searchByContinent() {
		Map<String, String> params = new HashMap<>();
		params.put("continent", "Asia");
		List<Country> countries = countryDao.getCountries(params);
		
		assertThat(countries).hasSize(51);
	}
	
	@Test
	public void testGetCountryDetail() {
		Country c = countryDao.getCountryDetail("IND");
		assertThat(c).isNotNull();
		assertThat(c.toString()).isEqualTo("Country(code=IND, name=India, "
				+ "continent=Asia, region=Southern and Central Asia, "
				+ "surfaceArea=3287263.0, indepYear=1947, population=1013662000, "
				+ "lifeExpectancy=62.5, gnp=447114.0, localName=Bharat/India, "
				+ "governmentForm=Federal Republic, headOfState=Kocheril Raman Narayanan, "
				+ "capital=City(id=1109, name=New Delhi, countryCode=null, "
				+ "country=null, district=null, population=null), code2=IN)");
	}
}
