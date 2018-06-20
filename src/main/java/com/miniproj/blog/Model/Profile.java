package com.miniproj.blog.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Profile {

	@Id
	@GeneratedValue 
	private int id;
	private String name;
	private String email;
	private String location;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile")
	Authentication authentication;
	
	public Profile() {
		
	}

	public Profile(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public Profile(String name, String email, String location) {
		super();
		this.name = name;
		this.email = email;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", email=" + email + ", location=" + location + "]";
	}
}
