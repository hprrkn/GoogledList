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
import com.prrknh.logic.CheckUtils;

@WebServlet("/WordListByTagServlet")
public class WordListByTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WordListByTagServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		
		req.setCharacterEncoding("UTF-8");
		
		// 指定タグのワードリスト取得
		if (req.getParameter("tagId") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		int tagId = Integer.parseInt(req.getParameter("tagId"));
		GoogledWordListDao gDao = new GoogledWordListDao();
		req.setAttribute("wordList", gDao.findWordListByTag(userMaster, tagId));
		TagMasterDao tDao = new TagMasterDao();
		TagMaster tag = tDao.getTagDetail(tagId);
		req.setAttribute("tag", tag);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/wordListByTag.jsp");
		dispatcher.forward(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
