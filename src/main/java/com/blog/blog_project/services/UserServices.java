package com.blog.blog_project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blog_project.payloads.UserDto;

@Service
public interface UserServices {

	UserDto creatUser(UserDto user);

	UserDto updateUser(UserDto user, Long id);

	UserDto getUserById(long id);

	List<UserDto> getAllUser();

	void deleteUserById(Long id);
}
