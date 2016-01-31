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

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.LoginCheck;

@WebServlet("/TagListServlet")
public class TagListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TagListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		UserMaster userMaster = LoginCheck.loginCheck(request, response);
		request.setCharacterEncoding("UTF-8");		
		
		TagMasterDao tDao = new TagMasterDao();
		List<TagMaster> allTagList = new ArrayList<>();
		allTagList = tDao.getAllTagList(userMaster);
		request.setAttribute("allTagList",allTagList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tagList.jsp");
		dispatcher.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		UserMaster userMaster = LoginCheck.loginCheck(request, response);
		
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		TagMasterDao tDao = new TagMasterDao();
		
		if (StringUtils.isNotEmpty(request.getParameter("delete_flg")) && request.getParameter("delete_flg").equals("true")){
			tDao.deleteTag(tagId);
			request.setAttribute("msg", "削除しました。");
		} else {
			String tagName = request.getParameter("tagName");
			tDao.editTag(tagId, tagName);
			request.setAttribute("msg", "更新しました。");
		} 
		
		List<TagMaster> allTagList = new ArrayList<>();
		allTagList = tDao.getAllTagList(userMaster);
		request.setAttribute("allTagList",allTagList);
		
		RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/tagList.jsp");
		dispathcer.forward(request,response);		
	}
}
