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
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;

@WebServlet("/WordDetailServlet")
public class WordDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WordDetailServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリファイレクト
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			response.sendRedirect("/GoogledList/TopPageServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");				
		Integer selectedId = Integer.parseInt(request.getParameter("id"));
		
		GoogledWordListDao gDao = new GoogledWordListDao(); 
		GoogledWord detail = gDao.findDetail(selectedId);
		TagMasterDao tDao = new TagMasterDao();
		List<TagMaster> tagList = tDao.getTagList(detail);
		request.setAttribute("detail", detail);
		request.setAttribute("tagList", tagList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/wordDetail.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
