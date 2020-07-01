package com.msdemo.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class MSFallbackController {
	
	@GetMapping("/account")
	public HashMap<String, String> accountFallback() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("reason", "Fallback");
		hashMap.put("status", "Not Found");
		return hashMap;
	}
	
	@GetMapping("/auth")
	public HashMap<String, String> authFallback() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("reason", "Fallback");
		hashMap.put("status", "Not Found");
		return hashMap;
	}
	
	@GetMapping("/user")
	public HashMap<String, String> userFallback() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("reason", "Fallback");
		hashMap.put("status", "Not Found");
		return hashMap;
	}
	
	

}
