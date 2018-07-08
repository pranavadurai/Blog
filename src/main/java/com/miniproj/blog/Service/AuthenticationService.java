package com.miniproj.blog.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Repository.AuthenticationRepository;

@Service
public class AuthenticationService {
	
	private Log logger = LogFactory.getLog(ProfileService.class);
	
	@Autowired
	private AuthenticationRepository authRepository;
	
	public Authentication getAuthUser(String remembertoken) {
		Authentication authentication = authRepository.findByRemembertoken(remembertoken);
	    return authentication;
	}
	
	public Boolean findByEmail(String email) {
		Authentication authentication = authRepository.findByEmail(email);
		
		if(authentication != null)
			return false;
		else 
		    return true; 
	}
	
	public Authentication loginUser(Authentication authentication) {
		logger.info("get Email:"+authentication.getEmail());
		Authentication auth = authRepository.findByEmail(authentication.getEmail());
		logger.info("Given Email:"+auth.getEmail());
		if(auth.getPassword().equals(authentication.getPassword())) 
			return auth;
		
		logger.info("Password INvalid");
		return new Authentication();
	}	
}
