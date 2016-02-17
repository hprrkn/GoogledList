package com.prrknh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.CheckUtils;

@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyPageServlet() {
        super();
    }
	
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック
		HttpSession session = req.getSession();
		if (session.getAttribute("userMaster") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
    	GoogledWordListDao gDao = new GoogledWordListDao();
    	int wordCount = gDao.getCountByUser(userMaster);
    	req.setAttribute("userMaster", userMaster);
    	req.setAttribute("wordCount", wordCount);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
