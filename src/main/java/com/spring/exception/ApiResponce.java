package com.spring.exception;

public class ApiResponce {
	private String message;
	boolean sucess;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSucess() {
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	
	
	public ApiResponce() {
		super();
		this.message = message;
		this.sucess = sucess;
	}
	
	public ApiResponce(String message, boolean sucess) {
		super();
		this.message = message;
		this.sucess = sucess;
	}
	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", sucess=" + sucess + "]";

}
	

}
