package com.blog.blog_project.config;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.blog_project.security.CustomeUserDetailService;
import com.blog.blog_project.security.JwtAuthenticationEntryPoint;
import com.blog.blog_project.security.JwtAutheticationFilter;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;

 

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private CustomeUserDetailService customeUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAutheticationFilter jwtAutheticationFilter;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customeUserDetailService);  // Link to your custom UserDetailsService
        authProvider.setPasswordEncoder( passwordEncoder());  // Use BCrypt for password hashing
        return authProvider;
    }

    // Method to expose the AuthenticationManager bean
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

     
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
	    .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
	    .exceptionHandling(exceptionHandling -> 
        exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint) // Set custom authentication entry point
	    		)
	    .authorizeHttpRequests(auth -> auth
	    	.requestMatchers("/api/v1/auth/login").permitAll()
	        .anyRequest().authenticated()  // All other requests require authentication
	    )
	    .sessionManagement(sessionManagement -> 
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
	    http.addFilterBefore(jwtAutheticationFilter, UsernamePasswordAuthenticationFilter.class);

	    
		
		 
		return http.build();

	}

	 
	 
}
