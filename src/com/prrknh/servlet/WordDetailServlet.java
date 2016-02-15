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
import com.prrknh.logic.CheckUtils;

@WebServlet("/WordDetailServlet")
public class WordDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WordDetailServlet() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
		}

		int selectedId = Integer.parseInt(CheckUtils.getParamChecker(req, res, "id"));
		GoogledWordListDao gDao = new GoogledWordListDao(); 
		GoogledWord detail = gDao.findDetail(selectedId);
		TagMasterDao tDao = new TagMasterDao();
		List<TagMaster> tagList = tDao.getTagList(detail);
		req.setAttribute("detail", detail);
		req.setAttribute("tagList", tagList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/wordDetail.jsp");
		dispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
