package com.miniproj.blog.Controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Model.Profile;
import com.miniproj.blog.Service.AuthenticationService;
import com.miniproj.blog.Service.PostService;
import com.miniproj.blog.Service.ProfileService;
import com.miniproj.blog.emailService.EmailServiceImpl;

import freemarker.template.TemplateException;

@Controller
@SessionAttributes("remembertoken")
public class AuthenticationController {
	
	@Autowired
	public AuthenticationService authService;
	
	@Autowired
	public ProfileService profileService;
	
	@Autowired
	public PostService postService;
	
	@Autowired
	public EmailServiceImpl emailService;
	
	private Log logger = LogFactory.getLog(AuthenticationController.class);
	
	@GetMapping("/")
	public String Showwelcomepage(ModelMap model) {
		logger.info("Entered into welcome Controller");
		Authentication authentication = authService.getAuthUser((String) model.get("remembertoken"));
		if(authentication==null)
			model.addAttribute("Auth_profile",new Profile());
		else
		    model.addAttribute("Auth_profile",authentication.getProfile());
		
	   return "home";
	}

	@GetMapping("/signup")
	public String Showsignuppage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signuptheuser(@RequestParam String name, @RequestParam String email, @RequestParam String password, ModelMap model) throws IOException, TemplateException, MessagingException {
		Authentication authentication = profileService.createUserAndAuthentication(name,email,password);
		if(authentication==null)
			return "redirect:/signup";
		model.addAttribute("remembertoken", authentication.getRemembertoken());
		return "redirect:/profile/"+authentication.getProfile().getId();
	}
			
	@GetMapping("/signin")
	public String Showsigninpage(ModelMap model) {
		model.addAttribute("Authentication",new Authentication());
		return "signin";
	}
	
	
	@GetMapping("/logout")
	public String Logouttheuser(SessionStatus status) {
		status.setComplete();
		logger.info("session cleared Logedout");
		return "redirect:/signin";
	}
}
