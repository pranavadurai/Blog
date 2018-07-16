package com.miniproj.blog.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.miniproj.blog.Model.Post;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post,Integer> {
	
	Post findById(int id);

}
