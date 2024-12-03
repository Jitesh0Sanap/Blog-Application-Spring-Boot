package com.blog.blog_project.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.blog_project.entities.User;
import com.blog.blog_project.exception.ResourceNotfoundException;
import com.blog.blog_project.payloads.UserDto;
import com.blog.blog_project.repository.UserRepo;
import com.blog.blog_project.services.UserServices;

@Component
public class UserServiceImpl implements UserServices {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto creatUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User saveUser = userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long id) {
		User user1 = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotfoundException("User" , "Id" , id));
		user1.setName(userDto.getName());
		user1.setEmail(userDto.getEmail());
		user1.setPassword(userDto.getPassword());
		user1.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user1);
		UserDto userDto1 = userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(long id) {
		User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotfoundException("User" , "Id" , id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> listOfUser = this.userRepo.findAll();
		List<UserDto> userDto = listOfUser.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUserById(Long id) {
		User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotfoundException("User" , "Id" , id));
		this.userRepo.deleteById(id);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
