package com.egen.orderservicee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerUiController {
	
	@RequestMapping(value = "/")
	public String inedx() {
		return "redirect:swagger-ui.html";
	}
}
