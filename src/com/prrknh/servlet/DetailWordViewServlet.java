package com.prrknh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.entity.GoogledWord;

@WebServlet("/detailWordViewServlet")
public class DetailWordViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailWordViewServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Integer selectedId = Integer.parseInt(request.getParameter("id"));
		
		GoogledWordListDao dao = new GoogledWordListDao(); 
		GoogledWord detail = dao.findDetail(selectedId);
		request.setAttribute("detail", detail);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detailview.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
