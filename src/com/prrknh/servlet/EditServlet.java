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


@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditServlet() {
        super();
    }

    // 編集/削除前
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
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
		
		RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
		dispathcer.forward(request,response);
	}

	// 編集/削除後
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		GoogledWordListDao gDao = new GoogledWordListDao();
		RelTagWordDao rDao = new RelTagWordDao();
		if (StringUtils.isNotEmpty(request.getParameter("delete_flg")) && request.getParameter("delete_flg").equals("true")){
			gDao.deleteDetail(id);
			rDao.deleteAllTagOnWord(id);
			request.setAttribute("msg", "削除しました。");
		} else {
			String editedWord = request.getParameter("editedword");
			String editedMemo = request.getParameter("editedmemo");
			
			// 配列からlist、list<String>からlist<Integer>という無駄な変換の嵐　直す :TODO
			String[] strTagIds = request.getParameterValues("tagId");
			List<String> strTagList = Arrays.asList(strTagIds);
			List<Integer> tagIdList = new ArrayList<>();
			for (String strTagId : strTagList){
				tagIdList.add(Integer.parseInt(strTagId));
			}
			gDao.updateDetail(id, editedWord, editedMemo);
			
			// 消して新たに追加し直してるから順番に注意　後で直す :TODO
			rDao.deleteAllTagOnWord(id);
			rDao.setTagOnWord(id,tagIdList);
			request.setAttribute("msg", "更新しました。");
		}
		RequestDispatcher dispathcer = request.getRequestDispatcher("/MainViewServlet");
		dispathcer.forward(request,response);
	}
}
