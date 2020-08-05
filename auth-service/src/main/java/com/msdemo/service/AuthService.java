package com.msdemo.service;

import java.util.Arrays;
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
import com.msdemo.model.Role;
import com.msdemo.model.User;
import com.msdemo.repo.RolesRepo;
import com.msdemo.repo.UserRepo;
import com.msdemo.utils.MSDemoUtils;

@Service
public class AuthService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RolesRepo rolesRepo;
	
	@Autowired
	private MSMailService mailService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Value("${msdemo-redirect-url}")
	private String redirectUrl;
	
	@Value("${msdemo-register-url}")
	private String registerUrl;
	
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

	public RegisterResponse register(RegisterRequest request) {
		RegisterResponse response = null;
		try{
			User user = null;
			if(userRepo.findByEmail(request.getEmail()) == null) {
				user = new User();
				user.setEmail(request.getEmail());
				user.setFirstName(request.getFirstName());
				user.setLastName(request.getLastName());
				user.setPhoneNumber(request.getPhoneNo());
				user.setPasword(MSDemoUtils.generatePassword(10));
				user.setFirstLogin(true);
				Role roles = rolesRepo.findByName("USER");
				user.setRoles(Arrays.asList(roles));
				userRepo.save(user);
				mailService.sendMail(user, registerUrl + tokenProvider.generateToken(user));
				response = new RegisterResponse(true, true, "", 200);
			} else {
				throw new Error("User already registered");
			}
		}
		catch(Exception ex) {
			throw new Error(ex.toString());
		}
		return response;
	}
}
