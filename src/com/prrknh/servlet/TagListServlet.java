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

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.CheckUtils;

@WebServlet("/TagListServlet")
public class TagListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TagListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック
		HttpSession session = req.getSession();
		if (session.getAttribute("userMaster") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		req.setCharacterEncoding("UTF-8");
		
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		TagMasterDao tDao = new TagMasterDao();
		List<TagMaster> allTagList = new ArrayList<>();
		allTagList = tDao.getAllTagList(userMaster);
		req.setAttribute("allTagList",allTagList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/tagList.jsp");
		dispatcher.forward(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		
		int tagId = Integer.parseInt(req.getParameter("tagId"));
		TagMasterDao tDao = new TagMasterDao();
		if (StringUtils.isNotEmpty(req.getParameter("delete_flg")) && req.getParameter("delete_flg").equals("true")){
			tDao.deleteTag(tagId);
			req.setAttribute("msg", "削除しました。");
		} else {
			String tagName = req.getParameter("tagName");
			tDao.editTag(tagId, tagName);
			req.setAttribute("msg", "更新しました。");
		} 
		
		List<TagMaster> allTagList = new ArrayList<>();
		allTagList = tDao.getAllTagList(userMaster);
		req.setAttribute("allTagList",allTagList);
		
		RequestDispatcher dispathcer = req.getRequestDispatcher("/WEB-INF/jsp/tagList.jsp");
		dispathcer.forward(req,res);
	}
}
