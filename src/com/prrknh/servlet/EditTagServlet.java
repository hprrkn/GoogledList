package com.prrknh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.TagMaster;

@WebServlet("/EditTagServlet")
public class EditTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditTagServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		
		TagMasterDao tDao = new TagMasterDao();
		TagMaster tagDetail = tDao.getTagDetail(tagId);
		request.setAttribute("tagDetail",tagDetail);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editTag.jsp");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
