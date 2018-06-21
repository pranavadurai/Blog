package com.miniproj.blog.Model;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Authentication {

	@Id 
	@GeneratedValue
	private int id;
	
	private String email;
	private String password;
	private boolean is_admin;
	private String remembertoken;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Profile profile;
	
	public Authentication () {
		
	}
	
	public Authentication(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.remembertoken = generate_remember_token();
	}
	
	public Authentication(int id, String email, String password, boolean is_admin, String remembertoken,
			Profile profile) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.is_admin = is_admin;
		this.remembertoken = remembertoken;
		this.profile = profile;
	}
	
	private String generate_remember_token() {
		SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[128];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    String token = encoder.encodeToString(bytes);
	    return token.substring(0, 40);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public String getRemembertoken() {
		return remembertoken;
	}

	public void setRemembertoken(String remembertoken) {
		this.remembertoken = remembertoken;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Authentication [id=" + id + ", email=" + email + ", password=" + password + ", is_admin=" + is_admin
				+ ", remembertoken=" + remembertoken + "]";
	}
}
