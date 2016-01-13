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
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.UserMaster;

@WebServlet("/MonthListViewServlet")
public class MonthListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MonthListViewServlet() {
        super();
    }
    
    // 指定月リストへ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		request.setCharacterEncoding("UTF-8");
		
		// 更新後に戻ってきた時用のメッセージ設定
		String msg = request.getParameter("msg");
		if (StringUtils.isNotEmpty(msg)) {
			request.setAttribute("msg", msg);
		}
		
		GoogledWordListDao dao = new GoogledWordListDao();
		String strDate = request.getParameter("date");
		request.setAttribute("strDate", strDate);
		List<GoogledWord> wordList = dao.findMonthList(userMaster, strDate);
		request.setAttribute("wordList", wordList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/monthListView.jsp");
		dispatcher.forward(request,response);
	}

	// 新規ワード追加で当月リストへ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		UserMaster userMaster = (UserMaster)session.getAttribute("userMaster");
		request.setCharacterEncoding("UTF-8");
		
		// formからの受け取り
		String addWord = request.getParameter("addWord");
		
		// 配列からlist、list<String>からlist<Integer>という無駄な変換の嵐　直す :TODO
		String memo = request.getParameter("memo");
		String[] strTagIds = request.getParameterValues("tagId");
		List<String> strTagList = Arrays.asList(strTagIds);
		List<Integer> tagIdList = new ArrayList<>();
		for (String tagId : strTagList){
			tagIdList.add(Integer.parseInt(tagId));
		}
		
	    // wordとtagのinsert
		GoogledWordListDao gDao =new GoogledWordListDao();
		TagMasterDao tmDao = new TagMasterDao();
	    tmDao.setTagOnWord(gDao.addWord(userMaster, addWord, memo),tagIdList);
		
		// 追加したものも含めて当月リスト取得
		List<GoogledWord> nowMonthList = gDao.findNowMonthView(userMaster);
		request.setAttribute("nowMonthList", nowMonthList);
	    request.setAttribute("addword", addWord);
	    request.setAttribute("memo", memo);
	    
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addedListView.jsp");
		dispatcher.forward(request,response);
		
	}

}
