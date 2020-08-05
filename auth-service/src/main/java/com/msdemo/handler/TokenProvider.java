package com.msdemo.handler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msdemo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 934163944225149922L;
	
	@Value("${msdemo-access-token-validity-seconds}")
	private int validitySeconds;

	@Value("${msdemo-signing-key}")
	private String signingKey;
	
	@Value("${msdemo-authority-key}")
	private String authorityKey;

	public String generateToken(User user) {
		final List<String> authorities = user.getRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());
        String token =  Jwts.builder()
                .setSubject(user.getEmail())
                .claim(authorityKey, authorities)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validitySeconds*1000))
                .compact();
        System.out.println("token " + token);
        return token;
	}
}
