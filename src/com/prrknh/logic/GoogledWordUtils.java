package com.prrknh.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoogledWordUtils {
	
	public static String dateFormat (String strDate){
		DateFormat fmt = new SimpleDateFormat("yyyy年MM月");
		return fmt.format(strDate);
	}
	
	public static String dateFormat (Date date){
		DateFormat fmt = new SimpleDateFormat("yyyy年MM月");
		return fmt.format(date);
	}

}
