package com.miniproj.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproj.blog.Model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
	
	Post findById(int id);

}
