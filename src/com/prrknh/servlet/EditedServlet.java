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

import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.dao.RelTagWordDao;

@WebServlet("/EditedServlet")
public class EditedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditedServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		GoogledWordListDao gDao = new GoogledWordListDao();
		RelTagWordDao rDao = new RelTagWordDao();
		if (StringUtils.isNotEmpty(request.getParameter("delete_flg")) && request.getParameter("delete_flg").equals("true")){
			gDao.deleteDetail(id);
			rDao.deleteTag(id);
			request.setAttribute("msg", "削除しました。");
		} else {
			String editedWord = request.getParameter("editedword");
			String editedMemo = request.getParameter("editedmemo");
			
			// 配列からlist、list<String>からlist<Integer>という無駄な変換の嵐　直す :TODO
			String[] strTagIds = request.getParameterValues("tagId");
			List<String> strTagList = Arrays.asList(strTagIds);
			List<Integer> tagIdList = new ArrayList<>();
			for (String strTagId : strTagList){
				int tagId = Integer.parseInt(strTagId);
				rDao.deleteTag(tagId);
				tagIdList.add(tagId);
			}
			
			gDao.updateDetail(id, editedWord, editedMemo);
			rDao.setTagOnWord(id,tagIdList);
			request.setAttribute("msg", "更新しました。");
		}
		RequestDispatcher dispathcer = request.getRequestDispatcher("/MainViewServlet");
		dispathcer.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
