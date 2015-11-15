package com.prrknh;

import java.io.Serializable;
import java.util.Date;

public class GoogledWord implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String word;
	private String memo;
	private Date added_day;
	
	public GoogledWord(){}
	
	public GoogledWord(int id, String word, String memo, Date added_day){
		this.id = id;
		this.word = word;
		this.memo = memo;
		this.added_day = added_day;
	}
	public int getId(){return id;}
	public String getWord(){return word;}
	public String getMemo(){return memo;}
	public Date getAdded_day(){return added_day;}
}
