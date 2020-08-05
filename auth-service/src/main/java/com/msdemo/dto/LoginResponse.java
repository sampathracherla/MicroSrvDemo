package com.msdemo.dto;

import java.util.Collection;
import java.util.List;

public class LoginResponse {

	private int status;
	private String redirectUrl;
	private String email;
	private List<String> roles;
	private String error;
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(int status, String error, String email) {
		this.status = status;
		this.error = error;
		this.email = email;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
