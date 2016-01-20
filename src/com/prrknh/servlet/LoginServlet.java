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
import com.prrknh.logic.LoginCheck;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request,response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ユーザー情報を取得
		String checkName = request.getParameter("userName");
		String checkPass = request.getParameter("userPass");
		UserMaster checkUser = new UserMaster(checkName, checkPass);
		
		// ユーザーチェック
		if(LoginCheck.checkUser(checkUser)){
			//月リストを取得
			UsersDao usersDao = new UsersDao();
			UserMaster userMaster = usersDao.getUserInfo(checkName);
			// sessionにユーザーマスターをセット
			HttpSession session = request.getSession();
			session.setAttribute("userMaster", userMaster);
			// forward
			RequestDispatcher dispatcher = request.getRequestDispatcher("TopPageServlet");
			dispatcher.forward(request,response);
		} else {
			request.setAttribute("msg", "ユーザーネームかパスワードが間違っています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request,response);
		}
	}

}
