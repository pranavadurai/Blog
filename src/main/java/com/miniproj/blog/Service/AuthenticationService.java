package com.miniproj.blog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Repository.AuthenticationRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationRepository authrepository;
	
	public Authentication getAuthUser(String remembertoken) {
		Authentication authentication = authrepository.findByRemembertoken(remembertoken);
	    return authentication;
	}
	
}
