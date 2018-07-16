package com.miniproj.blog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.miniproj.blog.Model.Post;
import com.miniproj.blog.Repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Page<Post> getAllPosts(Pageable pageable){
		return postRepository.findAll(pageable);
	}
}
