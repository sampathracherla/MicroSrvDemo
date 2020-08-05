package com.msdemo.dto;

public class RegisterResponse {

	private boolean isRegistered;
	private boolean emailStatus;
	
	public boolean isRegistered() {
		return isRegistered;
	}
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	public boolean isEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(boolean emailStatus) {
		this.emailStatus = emailStatus;
	}
	
	
}
