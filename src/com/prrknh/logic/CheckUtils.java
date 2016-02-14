package com.prrknh.logic;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.UsersDao;
import com.prrknh.entity.UserMaster;

public class CheckUtils {
	
	public  static boolean checkUser(UserMaster userMaster){
		UsersDao dao = new UsersDao();
		String gotPass = dao.getUserInfo(userMaster.getUserName()).getUserPass();
		if (StringUtils.isEmpty(gotPass)) {
			return false;
		}
		return gotPass.equals(userMaster.getUserPass()); 
	}

	public static UserMaster loginCheck(HttpServletRequest req, HttpServletResponse res) throws IOException{
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect("/GoogledList/TopPageServlet");
		}
		return userMaster;
	}

	public static String getParamChecker(HttpServletRequest req, HttpServletResponse res, String paramName) throws IOException{
		if (req.getParameter(paramName) == null){
			res.sendRedirect("/GoogledList/TopPageServlet");
		}
		return req.getParameter(paramName);
	}
}
