package com.prrknh;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


@WebServlet("/MainViewServlet")
public class MainViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("msg");
		if (StringUtils.isNotEmpty(msg)) request.setAttribute("msg", msg);
		GoogledWordListDao dao =new GoogledWordListDao();
		List<GoogledWord> wordlist = dao.findAll();
		request.setAttribute("wordlist", wordlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainview.jsp");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
