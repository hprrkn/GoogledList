package com.prrknh.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public final class GoogledWordUtils {

	private static final String DATE_FORMAT = "yyyy年MM月";
	private static final String DATE_REFORMAT = "yyyy年MM月dd日";
	private static final String DATE_DB_FORMAT = "yyyy/MM/dd";
	
	private GoogledWordUtils(){};

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
		return dateDBFormat(format.parse(recoveryFormatedDate(formatedDate)));
	}
	
	public static List<Integer> returnTagIdList (HttpServletRequest req){
		// 選択されたタグ
		List<Integer> tagIdList = new ArrayList<>();
		if (StringUtils.isNoneEmpty(req.getParameterValues("tagId"))){
			for (String strTagId : Arrays.asList(req.getParameterValues("tagId"))){
				tagIdList.add(Integer.parseInt(strTagId));
			}
		}
		return tagIdList;
	}
}
