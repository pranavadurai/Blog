package com.miniproj.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproj.blog.Model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
	
	Profile findById(int id);

}
