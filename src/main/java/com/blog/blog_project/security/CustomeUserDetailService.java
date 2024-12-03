package com.blog.blog_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blog_project.entities.User;
 
import com.blog.blog_project.repository.UserRepo;

@Service
public class CustomeUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username)
		        .orElseThrow(() -> new com.blog.blog_project.exception.UsernameNotFoundException("usrname", "email", username)); // Use a lambda to supply the exception
		System.out.println("User found: " + user.getEmail());
		return user;
	}
	
	 


}
