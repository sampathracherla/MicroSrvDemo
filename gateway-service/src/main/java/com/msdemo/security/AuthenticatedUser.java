package com.msdemo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticatedUser implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private List<SimpleGrantedAuthority> roles;
	private boolean isAuthenticated;

	AuthenticatedUser(String userName, List<SimpleGrantedAuthority> roles) {
		this.userName = userName;
		this.roles = roles;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return this.isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.isAuthenticated = arg0;
	}
}
