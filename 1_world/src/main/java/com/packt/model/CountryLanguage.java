package com.packt.model;

import lombok.Data;

@Data
public class CountryLanguage {

	private Country country;
	private String language;
	private String isOfficial;
	private Double percentage;
	
}
