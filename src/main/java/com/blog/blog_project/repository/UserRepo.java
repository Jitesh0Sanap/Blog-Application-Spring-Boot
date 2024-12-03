package com.blog.blog_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog_project.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	
	Optional<User> findByEmail(String email);
}
