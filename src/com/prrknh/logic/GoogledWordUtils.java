package com.prrknh.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoogledWordUtils {

	private static final String DATE_FORMAT = "yyyy年MM月";
	private static final String DATE_REFORMAT = "yyyy年MM月dd日";
	private static final String DATE_DB_FORMAT = "yyyy/MM/dd";

	private static String recoveryFormatedDate (String formatedDate){
		return formatedDate + "01日";
	}

	public static String dateFormat (String strDate){
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(strDate);
	}

	public static String dateFormat (Date date){
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(date);
	}
	
	public static String dateDBFormat (Date date){
		DateFormat fmt = new SimpleDateFormat(DATE_DB_FORMAT);
		return fmt.format(date);
	}

	public static String dateReformat (String formatedDate) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_REFORMAT);
		String a = recoveryFormatedDate(formatedDate);
		Date date = format.parse(a);
		return dateDBFormat(date);
	}
}
