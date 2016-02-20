package com.prrknh.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;


@WebServlet("/TopPageServlet")
public class TopPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TopPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doBoth(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doBoth(req, res);
	}
	
	private void doBoth(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		// ログインチェック
		if (req.getSession().getAttribute("userMaster") == null){
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(req,res);
			return;
		}

		UserMaster userMaster = (UserMaster)req.getSession().getAttribute("userMaster");
		// 追加時選択用のタグリスト取得
		TagMasterDao tmDao = new TagMasterDao();
		List<TagMaster> allTagList = tmDao.getAllTagList(userMaster);
		req.setAttribute("allTagList", allTagList);
		
		//月次カウントリスト取得
		GoogledWordListDao dao = new GoogledWordListDao();
		Map<String,Integer> countMap = dao.countAllMonthWord(userMaster);
		req.setAttribute("countMap", countMap);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/topPage.jsp");
		dispatcher.forward(req,res);
	}
}
