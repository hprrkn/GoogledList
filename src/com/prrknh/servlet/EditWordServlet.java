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

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.dao.RelTagWordDao;
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;


@WebServlet("/EditWordServlet")
public class EditWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditWordServlet() {
        super();
    }

    // 編集/削除前
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリファイレクト
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			response.sendRedirect("/GoogledList/TopPageServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		
		int selectedId = Integer.parseInt(request.getParameter("selectedId"));
		GoogledWordListDao gDao = new GoogledWordListDao();
		TagMasterDao tDao = new TagMasterDao();
		GoogledWord detail = gDao.findDetail(selectedId);
		List<TagMaster> allTagList = tDao.getAllTagList(userMaster);
		List<TagMaster> tagList = tDao.getTagList(detail);
		request.setAttribute("detail", detail);
		request.setAttribute("allTagList", allTagList);
		request.setAttribute("tagList", tagList);
		
		RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/editWord.jsp");
		dispathcer.forward(request,response);
	}

	// ワード編集/削除後にwordListへ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得　なかったらログイン画面へリファイレクト
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			response.sendRedirect("/GoogledList/TopPageServlet");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		GoogledWordListDao gDao = new GoogledWordListDao();
		RelTagWordDao rDao = new RelTagWordDao();
		
		if (StringUtils.isNotEmpty(request.getParameter("delete_flg")) && request.getParameter("delete_flg").equals("true")){
			gDao.deleteDetail(id);
			rDao.deleteAllTagOnWord(id);
			request.setAttribute("msg", "削除しました。");
		} else {
			String editedWord = request.getParameter("editedword");
			String editedMemo = request.getParameter("editedmemo");
			
			gDao.updateDetail(id, editedWord, editedMemo);
			
			if (StringUtils.isNotEmpty(request.getParameter("tagId"))){
				List<Integer> tagIdList = new ArrayList<>();
				for (String strTagId : Arrays.asList(request.getParameter("tagId"))){
					tagIdList.add(Integer.parseInt(strTagId));
				}
				// 消して新たに追加し直してるから順番に注意　後で直す :TODO
				rDao.deleteAllTagOnWord(id);
				rDao.setTagOnWord(id,tagIdList);
			}
			
			request.setAttribute("msg", "更新しました");
		}
		RequestDispatcher dispathcer = request.getRequestDispatcher("/WordListServlet");
		dispathcer.forward(request,response);
	}
}
