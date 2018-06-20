package com.miniproj.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

	@GetMapping("/Profile")
	public String showtheprofilepage() {
		return "profile";
	}
}
