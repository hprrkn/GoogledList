package com.prrknh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int selectedId = Integer.parseInt(request.getParameter("selectedId"));
		GoogledWordListDao dao = new GoogledWordListDao();
		GoogledWord googledWord = dao.findDetail(selectedId);
		request.setAttribute("googledWord", googledWord);
		RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
		dispathcer.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		GoogledWordListDao dao = new GoogledWordListDao();
		if (StringUtils.isNotEmpty(request.getParameter("delete_flg"))){
			dao.deleteDetail(id);
			request.setAttribute("msg", "削除しました。");
		} else {
			String editedWord = request.getParameter("editedword");
			String editedMemo = request.getParameter("editedmemo");
			dao.updateDetail(id, editedWord, editedMemo);
			request.setAttribute("msg", "更新しました。");
		}
		RequestDispatcher dispathcer = request.getRequestDispatcher("/MainViewServlet");
		dispathcer.forward(request,response);
	}
}
