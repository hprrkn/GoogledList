package com.prrknh.servlet;

import java.io.IOException;
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
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.LoginCheck;


@WebServlet("/MainViewServlet")
public class MainViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		
		// 更新後に戻ってきた時用のメッセージ設定
		String msg = request.getParameter("msg");
		if (StringUtils.isNotEmpty(msg)) request.setAttribute("msg", msg);
		
	    // 月リストを取得
		GoogledWordListDao dao =new GoogledWordListDao();
		List<GoogledWord> wordlist = dao.findAll(userMaster);
		request.setAttribute("wordlist", wordlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainview.jsp");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ユーザー情報を取得
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		UserMaster userMaster = new UserMaster(userName, userPass);
		
		// ユーザーチェック
		if(LoginCheck.checkUser(userMaster)){
			//月リストを取得
			GoogledWordListDao dao =new GoogledWordListDao();
			List<GoogledWord> wordlist = dao.findAll(userMaster);
			request.setAttribute("wordlist", wordlist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainview.jsp");
			dispatcher.forward(request,response);			
		} else {
			request.setAttribute("msg", "ユーザーネームかパスワードが間違っています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request,response);	
		}
			
	}

}
