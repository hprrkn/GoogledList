package com.prrknh.logic;

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.UsersDao;
import com.prrknh.entity.UserMaster;

public class LoginCheck {
	
	public  static boolean checkUser(UserMaster userMaster){
		UsersDao dao = new UsersDao();
		String gotPass = dao.getUserInfo(userMaster.getUserName()).getUserPass();
		if (StringUtils.isEmpty(gotPass)) {
			return false;
		}
		return gotPass.equals(userMaster.getUserPass()); 
	}
}
