package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

	@RequestMapping("/sun") 
	public String sun() {
		return "sun";
	}
	
}
