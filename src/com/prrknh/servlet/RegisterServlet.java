package com.prrknh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.UsersDao;
import com.prrknh.entity.UserMaster;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String registerName = request.getParameter("userName");
		String registerPass = request.getParameter("userPass");
		if (StringUtils.isBlank(registerName) || StringUtils.isBlank(registerPass)){
			request.setAttribute("msg", "ユーザーネームとパスワードを正しく入力してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request,response);			
		}
		UserMaster regiterUser = new UserMaster(registerName, registerPass);
	
		UsersDao usersDao = new UsersDao();
		if (usersDao.register(regiterUser)){
			request.setAttribute("msg", "新規登録できました。");
		} else {
			request.setAttribute("msg", "正しく登録できませんでした。");
		}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request,response);
		
	}

}
