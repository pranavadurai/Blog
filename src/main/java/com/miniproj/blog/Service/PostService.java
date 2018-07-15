package com.miniproj.blog.Service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproj.blog.Model.Post;
import com.miniproj.blog.Repository.PostRepository;

@Service
public class PostService {

	private Log logger = LogFactory.getLog(ProfileService.class);
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> getAllPosts(){
		logger.info("All post are requestd");
		List<Post> posts = postRepository.findAll();
		return posts;
	}
}
