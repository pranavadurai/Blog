package com.miniproj.blog.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Model.Profile;
import com.miniproj.blog.Repository.AuthenticationRepository;
import com.miniproj.blog.Repository.ProfileRepository;

@Service
public class ProfileService {
	
	private Log logger = LogFactory.getLog(ProfileService.class);

	@Autowired
	private AuthenticationRepository authRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public Authentication createUserAndAuthentication(String name,String email,String password) {
		Profile profile = new Profile(name,email);
		profileRepository.save(profile);
		logger.info("new user Created: "+name+ "Profile id: "+ profile.getId());
		Authentication authentication = new Authentication(email,password);
		authentication.setProfile(profile);
		authRepository.save(authentication);
		logger.info("Authentication created for the new user: "+name+ "Authe id :" +authentication.getId());
		return authentication;
	}
	
}
