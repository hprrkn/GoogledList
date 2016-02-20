package com.prrknh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prrknh.logic.CheckUtils;

@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SettingServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック
		if (req.getSession().getAttribute("userMaster") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
		dispatcher.forward(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック
		if (req.getSession().getAttribute("userMaster") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
	}

}
