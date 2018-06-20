package com.miniproj.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

	@GetMapping("/Signup")
	public String Showsignuppage() {
		return "signup";
	}
	
	@PostMapping("/Signup")
	public String signuptheuser() {
		return "signup";
	}
	
	@GetMapping("/Signin")
	public String Showsigninpage() {
		return "signin";
	}
	
	@PostMapping("/Signin")
	public String signintheuser() {
		return "signin";
	}
}
