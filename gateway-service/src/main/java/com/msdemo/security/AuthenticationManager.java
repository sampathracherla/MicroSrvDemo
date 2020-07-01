package com.msdemo.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.msdemo.handler.TokenProvider;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

	@Autowired
	private TokenProvider tokenProvider;

	@Value("${msdemo-authority-key}")
	private String authorityKey;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();
	
				
		UsernamePasswordAuthenticationToken auth = null;
		String userName = tokenProvider.getUsernameFromToken(authToken);
		 
		
				
		if (userName != null && !tokenProvider.isTokenExpired(authToken)) {
			Claims claims = tokenProvider.getallClaimsFromToken(authToken);
			List<String> roles = claims.get(authorityKey, List.class);
			List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
					.collect(Collectors.toList());
			auth = new UsernamePasswordAuthenticationToken(userName, authToken, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(new AuthenticatedUser(userName, authorities));
			
					
			return Mono.just(auth);
		}

		return Mono.just(auth);
	}

}
