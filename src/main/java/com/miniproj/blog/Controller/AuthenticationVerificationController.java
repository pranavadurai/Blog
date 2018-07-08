package com.miniproj.blog.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Service.AuthenticationService;

@RestController
@SessionAttributes("remembertoken")
public class AuthenticationVerificationController {

	private Log logger = LogFactory.getLog(AuthenticationVerificationController.class);

	@Autowired
	public AuthenticationService authService;
	
	@GetMapping("/check_email")
	public Boolean checkTheSignupEmailIsNew(@RequestParam String email) {
		logger.info("Check email called and the email id:"+email);
		Boolean found = authService.findByEmail(email);
		return found;
	}
	
	@PostMapping("/signin")
	public String signintheuser(@RequestBody Authentication authentication, ModelMap model) {
		Boolean found_email = authService.findByEmail(authentication.getEmail());
		logger.info(authentication.getEmail());
		logger.info(found_email);
		if(found_email)
			return "false";
		
		logger.info("Email verified");
		
		Authentication auth = authService.loginUser(authentication);
		logger.info("After Verification");
		if(auth.getEmail()==null)
			return "false";
			
		model.put("remembertoken", auth.getRemembertoken());
		logger.info("Remeber token:"+auth.getRemembertoken());
	    logger.info("Password verified & Remeber token retrived");
		return "/profile/"+auth.getProfile().getId();
	}
	
}
