package com.prrknh.entity;

import java.io.Serializable;

//import java.util.Date;

public class TagMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int tagId;
	private String tagName;
//	private String tagColor;
//	private Date tagRegiDate;
	private int cnt;
	
	public TagMaster(){}
	
	public TagMaster(int tagId, String tagName){
		this.tagId = tagId;
		this.tagName = tagName;		
	}
	
	public TagMaster(int tagId, String tagName, int cnt){
		this.tagId = tagId;
		this.tagName = tagName;
		this.cnt = cnt;
	}
	
	public int getTagId(){
		return this.tagId;
	}
	
	public void setTagId(int tagId){
		this.tagId = tagId;
	}
	
	public String getTagName(){
		return this.tagName;
	}
	
	public void setTagName(String tagName){
		this.tagName = tagName;
	}
	
	public int getCnt(){
		return this.cnt;
	}
	
	public void setCnt(int cnt){
		this.cnt = cnt;
	}
	
}
