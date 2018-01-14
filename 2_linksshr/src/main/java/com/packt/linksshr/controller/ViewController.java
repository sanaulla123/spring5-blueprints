package com.packt.linksshr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping(path = {"/", "/links"} )
	public String index() {
		return "links";
	}
}
