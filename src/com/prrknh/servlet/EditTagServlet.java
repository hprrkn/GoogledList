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
public class EditTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditTagServlet() {
        super();
    }
    
    // 選択タグの編集ページへ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリファイレクト
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			response.sendRedirect("/GoogledList/TopPageServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");		
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		
		TagMasterDao tDao = new TagMasterDao();
		TagMaster tagDetail = tDao.getTagDetail(tagId);
		request.setAttribute("tagDetail",tagDetail);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editTag.jsp");
		dispatcher.forward(request,response);
	}
	
	//　新規タグ追加にタグリストへ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリファイレクト
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			response.sendRedirect("/GoogledList/TopPageServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");		
		
		String addTagName = request.getParameter("addTagName");
		TagMasterDao tDao = new TagMasterDao();
		tDao.createTag(userMaster, addTagName);
		
		request.setAttribute("msg", "新規タグを追加しました。");
		
		List<TagMaster> allTagList = new ArrayList<>();
		allTagList = tDao.getAllTagList(userMaster);
		request.setAttribute("allTagList",allTagList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tagList.jsp");
		dispatcher.forward(request,response);
	}
}
