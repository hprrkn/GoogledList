package com.prrknh.entity;

import java.io.Serializable;
import java.util.Date;

public class MonthCnt implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private  String strYearMonth;
	private  int cnt;
	
	public MonthCnt(Date date, String strYearMonth, int cnt){
		this.date = date;
		this.strYearMonth = strYearMonth;
		this.cnt = cnt;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public void setDat(Date date){
		this.date = date;
	}
	
	public String getstrYearMonth(){
		return this.strYearMonth;
	}
	
	public void setYearMonth(String strYearMonth){
		this.strYearMonth = strYearMonth;
	}
	
	public int getCnt(){
		return this.cnt;
	}
	
	public void setCnt(int cnt){
		this.cnt = cnt;
	}
	
	
	
	
}
