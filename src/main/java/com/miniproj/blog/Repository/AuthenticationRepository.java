package com.miniproj.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproj.blog.Model.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication,Integer> {

}
