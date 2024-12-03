package com.blog.blog_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_project.payloads.JwtAuthRequest;
import com.blog.blog_project.payloads.JwtAuthResponce;
import com.blog.blog_project.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> creatToken(@RequestBody JwtAuthRequest request ) throws Exception{
		
		autheticate(request.getUsername(), request.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = jwtTokenHelper.generateToken(userDetails);	
		System.out.println("here is the token" +token);
		JwtAuthResponce responce = new JwtAuthResponce();
		
		responce.setToken(token);
		System.out.println("here is the responce" + token);
		return new ResponseEntity<JwtAuthResponce>(responce , HttpStatus.OK);
	}


	private ResponseEntity<String> autheticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		 
			try {
				authenticationManager.authenticate(authenticationToken);
				 System.out.println("User authenticated successfully.");
			} catch (BadCredentialsException e) {
				System.out.println("Invalid credentials: " + username);
				return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);

			}catch (Exception e) {
		        // Catch any other exceptions and return internal server error
		        return new ResponseEntity<>("An error occurred while processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
		    }
			return null;
		 		
		
	}

}
