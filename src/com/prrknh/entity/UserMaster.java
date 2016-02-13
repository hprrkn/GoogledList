package com.prrknh.entity;

public class UserMaster {
	private int userId;
	private String userName;
	private String userPass;
	private String registerDate;
	private int wordCount;
	
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

	public int getWordCount(){
		return this.wordCount;
	}

	public void setWordCount(int wordCount){
		this.wordCount = wordCount;
	}
}
