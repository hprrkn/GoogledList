package com.prrknh;

import java.io.Serializable;
import java.util.Date;

public class GoogledWord implements Serializable{
 	private int id;
	private String word;
	private String memo;
	private Date resister_day;
	
	public GoogledWord(){}
	
	public GoogledWord(int id, String word, String memo, Date resister_day){
		this.id = id;
		this.word = word;
		this.memo = memo;
		this.resister_day = resister_day;
	}
	public int getId(){return id;}
	public String getWord(){return word;}
	public String getMemo(){return memo;}
	public Date getCreated(){return resister_day;}
}
