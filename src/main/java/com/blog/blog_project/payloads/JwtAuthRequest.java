package com.blog.blog_project.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
	private String username;
	private String password;

}
