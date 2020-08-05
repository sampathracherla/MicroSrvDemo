package com.msdemo.dto;

public class RegisterResponse {

	private boolean isRegistered;
	private boolean emailStatus;
	private String errorMsg;
	private int status;
	
	public RegisterResponse() {
		
	}
	
	public RegisterResponse(boolean isRegistered, boolean emailStatus, String errorMsg, int status) {
		this.isRegistered = isRegistered;
		this.emailStatus = emailStatus;
		this.errorMsg = errorMsg;
		this.status = status;
	}
	
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

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
