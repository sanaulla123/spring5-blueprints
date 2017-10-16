package com.packt.model;

import lombok.Data;

@Data
public class City {

	private Long id;
	private String name;
	private Country country;
	private String district;
	private Long population;
}
