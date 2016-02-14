package com.prrknh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prrknh.dao.UsersDao;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.CheckUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ユーザー情報を取得
		String checkName = req.getParameter("userName");
		String checkPass = req.getParameter("userPass");
		UserMaster checkUser = new UserMaster(checkName, checkPass);
		
		// ユーザーチェック
		if(CheckUtils.checkUser(checkUser)){
			//月リストを取得
			UsersDao usersDao = new UsersDao();
			UserMaster userMaster = usersDao.getUserInfo(checkName);
			// sessionにユーザーマスターをセット
			HttpSession session = req.getSession();
			session.setAttribute("userMaster", userMaster);
			// forward
			RequestDispatcher dispatcher = req.getRequestDispatcher("TopPageServlet");
			dispatcher.forward(req,res);
		} else {
			req.setAttribute("msg", "ユーザーネームかパスワードが間違っています。");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(req,res);
		}
	}

}
