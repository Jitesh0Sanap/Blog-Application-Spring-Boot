package com.blog.blog_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_project.payloads.ApiResponce;
import com.blog.blog_project.payloads.UserDto;
import com.blog.blog_project.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/create-user")
	public ResponseEntity<UserDto> creatUser(@Valid @RequestBody UserDto userDto){
		UserDto creatUserDto = userServices.creatUser(userDto);
		return new ResponseEntity<>(creatUserDto , HttpStatus.CREATED);
	}
	
	@PutMapping("/update-user/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable long id){
		UserDto updateUser = this.userServices.updateUser(userDto, id);
		return ResponseEntity.ok(updateUser);
	}
	
	@GetMapping("/userById/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable long id){
		return ResponseEntity.ok(this.userServices.getUserById(id));
	}
	
	@GetMapping("/alluser")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(this.userServices.getAllUser());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete-user-by/{id}")
	public ResponseEntity<ApiResponce> deleteUserByid(@PathVariable long id){
		this.userServices.deleteUserById(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("UserDeleted Successfully", true),HttpStatus.OK );
	}
	
	

}
