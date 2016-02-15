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
import com.prrknh.logic.CheckUtils;

@WebServlet("/EditTagServlet")
public class EditTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditTagServlet() {
        super();
    }
    
    // 選択タグの編集ページへ
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
		}
		
		int tagId = Integer.parseInt(CheckUtils.getParamChecker(req, res, "tagId"));
		
		TagMasterDao tDao = new TagMasterDao();
		TagMaster tagDetail = tDao.getTagDetail(tagId);
		req.setAttribute("tagDetail",tagDetail);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/editTag.jsp");
		dispatcher.forward(req,res);
	}
	
	// 新規タグ追加にタグリストへ
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
		}
		req.setCharacterEncoding("UTF-8");
		
		String addTagName = req.getParameter("addTagName");
		TagMasterDao tDao = new TagMasterDao();
		tDao.createTag(userMaster, addTagName);
		
		req.setAttribute("msg", "新規タグを追加しました。");
		
		List<TagMaster> allTagList = new ArrayList<>();
		allTagList = tDao.getAllTagList(userMaster);
		req.setAttribute("allTagList",allTagList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/tagList.jsp");
		dispatcher.forward(req,res);
	}
}
