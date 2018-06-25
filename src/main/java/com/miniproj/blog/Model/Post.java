package com.miniproj.blog.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private int id;
	
	private String tweet;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Profile profile;
	
	public Post() {
		
	}
	
	public Post(String tweet) {
		super();
		this.tweet = tweet;
	}

	public Post(int id, String tweet, Profile profile) {
		super();
		this.id = id;
		this.tweet = tweet;
		this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", tweet=" + tweet + "]";
	}
}
