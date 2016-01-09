package com.prrknh.entity;

import java.io.Serializable;

public class MonthCnt implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private  String yearMonth;
	private  int cnt;
	
	public MonthCnt(String yearMonth, int cnt){
		this.yearMonth = yearMonth;
		this.cnt = cnt;
	}
	
	public String getYearMonth(){
		return this.yearMonth;
	}
	
	public void setYearMonth(String yearMonth){
		this.yearMonth = yearMonth;
	}
	
	public int getCnt(){
		return this.cnt;
	}
	
	public void setCnt(int cnt){
		this.cnt = cnt;
	}
	
	
	
	
}
