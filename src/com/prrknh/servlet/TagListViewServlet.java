package com.prrknh.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;

@WebServlet("/EditTagServlet")
public class TagListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TagListViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		request.setCharacterEncoding("UTF-8");
		
		TagMasterDao tDao = new TagMasterDao();
		List<TagMaster> allTagList = new ArrayList<>();
		allTagList = tDao.getAllTagList(userMaster);
		request.setAttribute("allTagList",allTagList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tagList.jsp");
		dispatcher.forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
