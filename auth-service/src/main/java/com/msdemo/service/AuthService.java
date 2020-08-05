package com.msdemo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.msdemo.dto.LoginRequest;
import com.msdemo.dto.LoginResponse;
import com.msdemo.dto.RegisterRequest;
import com.msdemo.dto.RegisterResponse;
import com.msdemo.handler.TokenProvider;
import com.msdemo.model.User;
import com.msdemo.repo.UserRepo;

@Service
public class AuthService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Value("${msdemo-redirect-url}")
	private String redirectUrl;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public LoginResponse login(LoginRequest request) {
		LoginResponse response = null;
		try {
			User user = userRepo.findByEmail(request.getEmail());
			if (user != null) {
				if(passwordEncoder.matches(request.getPassword(), user.getPasword())) {
					response = new LoginResponse();
					response.setEmail(request.getEmail());
					response.setError("");
					response.setRoles(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()));
					response.setStatus(200);
					response.setRedirectUrl(redirectUrl + tokenProvider.generateToken(user));
				}
				else {
					throw new Error("Password Incorrect");
				}
			} else {
				throw new Error("User doesn't exist");
			}
		} catch (Exception ex) {
			throw new Error("Unable to login " + ex.toString());
		}
		return response;
	}

//	private RegisterResponse register(RegisterRequest request) {
//
//	}
}
