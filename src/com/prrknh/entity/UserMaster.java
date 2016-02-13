package com.prrknh.entity;

import java.io.Serializable;

public class UserMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String userPass;
	private String registerDate;
	
	public UserMaster(){
	}
	
	public UserMaster(String userName, String userPass){
		this.userName = userName;
		this.userPass = userPass;
	}
	
	public int getUserId(){
		return this.userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserPass(){
		return this.userPass;
	}
	
	public void setUserPass(String userPass){
		this.userPass = userPass;
	}

	public String getRegisterDate(){
		return registerDate;
	}

	public void setRegisterDate(String registerDate){
		this.registerDate = registerDate;
	}
}
