package com.jwttoken.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {
	
	
	@Value("${app.secret}")
	private String secret1;

	
	public boolean validateToken(String token, String username) {
		String tokenUser = getUsername(token);
	
		return (tokenUser.equals(username) && !isTokenExp(token));

	}


	public boolean isTokenExp(String token) {
		return getExpDate(token).before(new Date(System.currentTimeMillis()));
	}

	
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();

	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}


	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(Base64.getDecoder().decode(secret1.getBytes())).parseClaimsJws(token)
				.getBody();

	}

	
	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject).setIssuer("R.K.")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
				.signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(secret1.getBytes())).compact();

	}
}
