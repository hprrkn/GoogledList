package com.prrknh.logic;

import com.prrknh.dao.UsersDao;
import com.prrknh.entity.UserMaster;

public class LoginCheck {
	
	public  static boolean checkUser(UserMaster userMaster){
		// ユーザーネームからユーザー情報を取得
		UsersDao usersDao = new UsersDao();
		UserMaster a = usersDao.getUserInfo(userMaster.getUserName());
		String b = a.getUserPass();
		return b.equals(userMaster.getUserPass());
	}
}
