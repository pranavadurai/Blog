package com.miniproj.blog.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.miniproj.blog.ExceptionHandlers.ProfileNotFoundException;
import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Model.Profile;
import com.miniproj.blog.Service.AuthenticationService;
import com.miniproj.blog.Service.ProfileService;

@Controller
@SessionAttributes("remembertoken")
public class ProfileController {

	private Log logger = LogFactory.getLog(ProfileController.class);
			
	@Autowired
	public AuthenticationService authService;
	
	@Autowired
	public ProfileService profileService;
	
	@GetMapping("/profile/{id}")
	public String showtheprofilepage(ModelMap model,@PathVariable int id) {
		logger.info("Entered into profile Controller");
		Authentication authentication = authService.getAuthUser((String) model.get("remembertoken"));
		if(authentication == null)
			return "redirect:/signin";
		
		logger.info("Found the Authentication:"+ authentication.getProfile().getId());
		Profile profile = profileService.getProfile(id);
		if(profile ==null)
			throw new ProfileNotFoundException("Profile with the id: "+id+" Not found");
		
		logger.info("Found profile:"+profile);
		model.addAttribute("Auth_profile",authentication.getProfile());
		model.addAttribute("profile",profile);
		model.addAttribute("profile_posts",profile.getPosts());
		return "profile";
	}
}
