package com.prrknh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.TagMaster;


@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int selectedId = Integer.parseInt(request.getParameter("selectedId"));
		GoogledWordListDao gDao = new GoogledWordListDao();
		TagMasterDao tDao = new TagMasterDao();
		GoogledWord detail = gDao.findDetail(selectedId);
		List<TagMaster> allTagList = tDao.getAllTagList();
		List<TagMaster> tagList = tDao.getTagList(detail);
		request.setAttribute("detail", detail);
		request.setAttribute("allTagList", allTagList);
		request.setAttribute("tagList", tagList);
		
		RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
		dispathcer.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
