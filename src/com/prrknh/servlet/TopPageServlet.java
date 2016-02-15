package com.prrknh.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.MonthCnt;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.GoogledWordUtils;
import com.prrknh.logic.CheckUtils;


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
		// セッションからユーザー情報を取得　なかったらログイン画面へリダイレクト
		HttpSession session = req.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		if (userMaster == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		
		// 追加時選択用のタグリスト取得
		TagMasterDao tmDao = new TagMasterDao();
		List<TagMaster> allTagList = tmDao.getAllTagList(userMaster);
		req.setAttribute("allTagList", allTagList);
		
		//月次カウントリスト取得
		GoogledWordListDao dao = new GoogledWordListDao();
		Map<Date,Integer> countMap = dao.countAllMonthWord(userMaster);
		List<MonthCnt> monthCntList = new ArrayList<>();
		
		for (Entry<Date, Integer> entry : countMap.entrySet()){
			MonthCnt mcs = new MonthCnt(
					entry.getKey(),
					GoogledWordUtils.dateFormat(entry.getKey()),
					entry.getValue()
					); 	
			monthCntList.add(mcs);
		}
		req.setAttribute("monthCntList", monthCntList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/topPage.jsp");
		dispatcher.forward(req,res);
	}
}
