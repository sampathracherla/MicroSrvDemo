package com.msdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;

import com.msdemo.dto.LoginRequest;
import com.msdemo.dto.LoginResponse;
import com.msdemo.dto.RegisterResponse;
import com.msdemo.service.AuthService;

import reactor.core.publisher.Mono;

@Controller
public class AuthController {

	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/login")
	@ResponseBody
	private Mono<LoginResponse> login(ServerWebExchange exchange) {
		Mono<MultiValueMap<String, String>> formData = exchange.getFormData();
		return formData.flatMap(data -> {
			System.out.println("data "+ data);
			System.out.println("email "+ data.get("email"));
			System.out.println("email "+ data.getFirst("email"));
			if(data != null) {
				LoginRequest request = new LoginRequest();
				request.setEmail(data.getFirst("email").toString());
				request.setPassword(data.getFirst("password"));
				try {
					LoginResponse response = authService.login(request);
					if(response != null) {
						return Mono.just(response);
					} else {
						return Mono.just(new LoginResponse(403, "Invalid Credentials", data.getFirst("email").toString()));
					}
				}
				catch(Exception ex) {
					return Mono.just(new LoginResponse(403, ex.toString(), data.getFirst("email").toString()));
				}
			}
			return Mono.just(new LoginResponse(404, "Unable to Login", data.getFirst("email").toString()));
		});
	}
	
//	@ResponseBody
//	@PostMapping("/register")
//	private Mono<RegisterResponse> register(ServerWebExchange exchange) {
//		
//	}
	
}
