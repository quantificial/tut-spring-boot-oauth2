package com.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/hello")
	public String Hello(HttpServletRequest request) {
		
		int maxInactiveInterval = request.getSession().getMaxInactiveInterval();
		
		long last = request.getSession().getLastAccessedTime();
		
		System.out.println("###################################");
		System.out.println(maxInactiveInterval );
		System.out.println(last);
		
		String s = maxInactiveInterval + " " + last;
		
		return s;
	}
}
