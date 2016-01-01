package com.prrknh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.UserMaster;

@WebServlet("/MonthListViewServlet")
public class MonthListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MonthListViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		
		request.setCharacterEncoding("UTF-8");
		
		String addWord = request.getParameter("addWord");
		String memo = request.getParameter("memo");
	    
		GoogledWordListDao dao =new GoogledWordListDao();
		dao.addWord(userMaster, addWord, memo);
		List<GoogledWord> nowMonthList = dao.findNowMonthView(userMaster);
		request.setAttribute("nowMonthList", nowMonthList);
	    request.setAttribute("addword", addWord);
	    request.setAttribute("memo", memo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addedListView.jsp");
		dispatcher.forward(request,response);
		
	}

}
