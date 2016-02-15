package com.prrknh.logic;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.UsersDao;
import com.prrknh.entity.UserMaster;

public class CheckUtils {
	
	public static final String TOP_PAGE_URL = "/GoogledList/TopPageServlet"; 
	
	public  static boolean checkUser(UserMaster userMaster){
		UsersDao dao = new UsersDao();
		String gotPass = dao.getUserInfo(userMaster.getUserName()).getUserPass();
		if (StringUtils.isEmpty(gotPass)) {
			return false;
		}
		return gotPass.equals(userMaster.getUserPass()); 
	}

	public static String getParamChecker(HttpServletRequest req, HttpServletResponse res, String paramName) throws IOException{
		if (req.getParameter(paramName) == null){
			res.sendRedirect("/GoogledList/TopPageServlet");
		}
		return req.getParameter(paramName);
	}
}
