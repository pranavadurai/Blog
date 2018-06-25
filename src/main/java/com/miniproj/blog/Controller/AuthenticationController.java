package com.miniproj.blog.Controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Model.Profile;
import com.miniproj.blog.Repository.AuthenticationRepository;
import com.miniproj.blog.Repository.ProfileRepository;
import com.miniproj.blog.Service.AuthenticationService;
import com.miniproj.blog.emailService.EmailServiceImpl;

import freemarker.template.TemplateException;

@Controller
@SessionAttributes("remembertoken")
public class AuthenticationController {

	@Autowired
	public AuthenticationRepository authRepository;
	
	@Autowired
	public ProfileRepository profileRepository;
	
	@Autowired
	public AuthenticationService authService;
	
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
		return "welcome";
	}

	@GetMapping("/signup")
	public String Showsignuppage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signuptheuser(@RequestParam String name, @RequestParam String email, @RequestParam String password, ModelMap model) throws IOException, TemplateException, MessagingException {
		logger.info("Signup Controller invoked for the new user: "+name);
		Profile profile = new Profile(name,email);
		profileRepository.save(profile);
		logger.info("new user Created: "+name+ "Profile id: "+ profile.getId());
		Authentication authentication = new Authentication(email,password);
		authentication.setProfile(profile);
		authRepository.save(authentication);
		emailService.sendWelcomeMessage(email,name);
		logger.info("Authentication created for the new user: "+name+ "Authe id :" +authentication.getId());
		model.addAttribute("remembertoken", authentication.getRemembertoken());
		return "redirect:/profile/"+authentication.getProfile().getId();
	}
		
	@GetMapping("/signin")
	public String Showsigninpage(ModelMap model) {
		model.addAttribute("Authentication",new Authentication());
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signintheuser(@ModelAttribute Authentication authentication, ModelMap model) {
		Authentication auth = authRepository.findByEmail(authentication.getEmail());
		if(auth == null)
			return "redirect:/signin";
		
		logger.info("Email verified");
		if(auth.getPassword().equals(authentication.getPassword())) {
			model.put("remembertoken", auth.getRemembertoken());
		    logger.info("Password verified & Remeber token retrived");
			return "redirect:/profile/"+auth.getProfile().getId();
		}
			
		return "redirect:/signin";
	}
	
	@GetMapping("/logout")
	public String Logouttheuser(SessionStatus status) {
		status.setComplete();
		logger.info("session cleared Logedout");
		return "redirect:/signin";
	}
}
