package com.blog.blog_project.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	private String secret = "jwtTokenKey";
	
	public String getUsernameFromToken(String token) {
		return getClaimFromTaken(token , Claims::getSubject );
	}
	
	public Date getExpirationDataFromToken(String token) {
		return getClaimFromTaken(token , Claims::getExpiration );
	}
	
	public <T> T getClaimFromTaken(String token , Function<Claims, T> claimResolver ) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDataFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return doGenerateToken(claims , userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
	
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String extractedUsername = getUsernameFromToken(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
