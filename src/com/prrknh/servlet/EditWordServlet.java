package com.prrknh.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.dao.RelTagWordDao;
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.CheckUtils;


@WebServlet("/EditWordServlet")
public class EditWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditWordServlet() {
        super();
    }

    // 編集/削除前
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		req.setCharacterEncoding("UTF-8");
		
		if (req.getParameter("selectedId") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		int selectedId = Integer.parseInt(req.getParameter("selectedId"));
		GoogledWordListDao gDao = new GoogledWordListDao();
		TagMasterDao tDao = new TagMasterDao();
		GoogledWord detail = gDao.findDetail(selectedId);
		List<TagMaster> allTagList = tDao.getAllTagList(userMaster);
		List<TagMaster> tagList = tDao.getTagList(detail);
		req.setAttribute("detail", detail);
		req.setAttribute("allTagList", allTagList);
		req.setAttribute("tagList", tagList);
		
		RequestDispatcher dispathcer = req.getRequestDispatcher("/WEB-INF/jsp/editWord.jsp");
		dispathcer.forward(req,res);
	}

	// ワード編集/削除後にwordListへ
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		
		req.setCharacterEncoding("UTF-8");
		if (req.getParameter("id") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("id", id);
		GoogledWordListDao gDao = new GoogledWordListDao();
		RelTagWordDao rDao = new RelTagWordDao();
		
		if (StringUtils.isNotEmpty(req.getParameter("delete_flg")) && req.getParameter("delete_flg").equals("true")){
			gDao.deleteDetail(id);
			rDao.deleteAllTagOnWord(id);
			req.setAttribute("msg", "削除しました。");
		} else {
			String editedWord = req.getParameter("editedword");
			String editedMemo = req.getParameter("editedmemo");
			
			gDao.updateDetail(id, editedWord, editedMemo);
			
			if (ArrayUtils.isNotEmpty(req.getParameterValues("tagId"))){
				List<Integer> tagIdList = new ArrayList<>();
				for (String strTagId : Arrays.asList(req.getParameterValues("tagId"))){
					tagIdList.add(Integer.parseInt(strTagId));
				}
				// 消して新たに追加し直してるから順番に注意　後で直す :TODO
				rDao.deleteAllTagOnWord(id);
				rDao.setTagOnWord(id,tagIdList);
			}
			
			req.setAttribute("msg", "更新しました");
		}
		RequestDispatcher dispathcer = req.getRequestDispatcher("/WordListServlet");
		dispathcer.forward(req,res);
	}
}
