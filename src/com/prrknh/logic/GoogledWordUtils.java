package com.prrknh.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoogledWordUtils {
	
	private static final String DATE_FORMAT = "yyyy年MM月";

	public static String dateFormat (String strDate){
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(strDate);
	}
	
	public static String dateFormat (Date date){
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(date);
	}

}
