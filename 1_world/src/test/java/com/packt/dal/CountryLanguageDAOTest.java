package com.packt.dal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.packt.config.TestDBConfiguration;
import com.packt.model.CountryLanguage;

@RunWith(SpringRunner.class)
@SpringJUnitConfig( classes = {TestDBConfiguration.class, CountryLanguageDAO.class})
public class CountryLanguageDAOTest {
	
	@Autowired CountryLanguageDAO countryLangDao;
	
	@Autowired @Qualifier("testTemplate")
	NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	@Before
	public void before() {
		countryLangDao.setNamedParamJdbcTemplate(namedParamJdbcTemplate);
	}
	
	@Test public void testGetLanguages() {
		List<CountryLanguage> languages = countryLangDao.getLanguages("IND");
		assertThat(languages).hasSize(12);
	}
	
	@Test public void testAddLanguage() {
		String countryCode = "IND";
		CountryLanguage cl = new CountryLanguage();
		cl.setCountryCode(countryCode);
		cl.setIsOfficial("T");
		cl.setLanguage("Test");
		cl.setPercentage(12.3);
		countryLangDao.addLanguage(countryCode, cl);
		List<CountryLanguage> languages = countryLangDao.getLanguages(countryCode);
		assertThat(languages).hasSize(13);
	}
	
	@Test public void testDeleteLanguage() {
		String countryCode = "IND";
		
		CountryLanguage cl = new CountryLanguage();
		cl.setCountryCode(countryCode);
		cl.setIsOfficial("T");
		cl.setLanguage("Test");
		cl.setPercentage(12.3);
		countryLangDao.addLanguage(countryCode, cl);
		List<CountryLanguage> languages = countryLangDao.getLanguages(countryCode);
		assertThat(languages).hasSize(13);
		
		countryLangDao.deleteLanguage(countryCode, "Test");
		languages = countryLangDao.getLanguages(countryCode);
		assertThat(languages).hasSize(12);
			
	}
}
