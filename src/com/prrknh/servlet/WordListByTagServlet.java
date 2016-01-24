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
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;

@WebServlet("/WordListByTagServlet")
public class WordListByTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WordListByTagServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		request.setCharacterEncoding("UTF-8");
		
		// 指定タグのワードリスト取得
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		GoogledWordListDao gDao = new GoogledWordListDao();
		request.setAttribute("wordList", gDao.findWordListByTag(userMaster, tagId));
		TagMasterDao tDao = new TagMasterDao();
		TagMaster tag = tDao.getTagDetail(tagId);
		request.setAttribute("tag", tag);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/wordListByTag.jsp");
		dispatcher.forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
