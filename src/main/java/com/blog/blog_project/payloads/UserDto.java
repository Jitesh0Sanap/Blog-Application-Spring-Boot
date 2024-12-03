package com.blog.blog_project.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private long id;
	
	@NotEmpty
	@Size(min=4 , message = "userame must be min 4 characters !! ")
	private String name;
	
	@Email(message = "email address is not valide !! ")
	private String email;
	
	@NotEmpty
	@Size(min=3 , max=10 , message = "passwors must be in between 3 to 10 character !! ")
	private String password;
	
	@NotEmpty
	private String about;
}
