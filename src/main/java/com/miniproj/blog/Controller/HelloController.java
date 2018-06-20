package com.miniproj.blog.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@GetMapping("/hello")
	public String printhelloworld() {
		return "hello-Pranavadurai version 5 ";
	}
}
