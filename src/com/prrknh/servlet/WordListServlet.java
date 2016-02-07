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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.prrknh.dao.GoogledWordListDao;
import com.prrknh.dao.RelTagWordDao;
import com.prrknh.dao.TagMasterDao;
import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.UserMaster;
import com.prrknh.logic.GoogledWordUtils;
import com.prrknh.logic.LoginCheck;

@WebServlet("/WordListServlet")
public class WordListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WordListServlet() {
        super();
    }
    
    // 指定月リストへ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		UserMaster userMaster = LoginCheck.loginCheck(request, response);
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/wordList.jsp");
		dispatcher.forward(request,response);
	}

	// ワード追加/編集で該当月リストへ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		UserMaster userMaster = LoginCheck.loginCheck(request, response);
		request.setCharacterEncoding("UTF-8");
		
		GoogledWordListDao gDao =new GoogledWordListDao();
		RelTagWordDao rDao = new RelTagWordDao();
		TagMasterDao tDao = new TagMasterDao();
		
		// 新規ワード追加の場合
		if (StringUtils.isNotEmpty(request.getParameter("addWord"))){	
			// formからの受け取り
			String addWord = request.getParameter("addWord");
			String memo = request.getParameter("memo");
			List<Integer> tagIdList = new ArrayList<>();
			
			// 配列からlist、list<String>からlist<Integer>という無駄な変換の嵐　直す :TODO
			if (StringUtils.isNoneEmpty(request.getParameterValues("tagId"))){
				for (String strTagId : Arrays.asList(request.getParameterValues("tagId"))){
					tagIdList.add(Integer.parseInt(strTagId));
				}
			}
			
			// 新規タグ追加
			if (StringUtils.isNotEmpty(request.getParameter("newTag"))){
				int tagId = tDao.createTag(userMaster, request.getParameter("newTag"));
				tagIdList.add(tagId);
			}
			
			int addedId = gDao.addWord(userMaster, addWord, memo);
			String strDate = GoogledWordUtils.dateFormat(gDao.findDetail(addedId).getAdded_day());
			request.setAttribute("strDate",strDate);
		    // wordとtagのinsert
			if (CollectionUtils.isNotEmpty(tagIdList)){
				rDao.setTagOnWord(addedId,tagIdList);
			}
			request.setAttribute("addWord", addWord);
		    request.setAttribute("memo", memo);
		}
		
		// ワード編集/削除の場合
		if (StringUtils.isNotEmpty(request.getParameter("id"))){
			String strDate = GoogledWordUtils.dateFormat(gDao.findDetail(Integer.parseInt(request.getParameter("id"))).getAdded_day());
			request.setAttribute("strDate",strDate);
			if (StringUtils.isNotEmpty(request.getParameter("msg"))){
				request.setAttribute("msg", request.getParameter("msg"));
			}
		}
		
		// 追加/編集したものも含めて該当月リスト取得
		List<GoogledWord> wordList = gDao.findNowMonthView(userMaster);
		request.setAttribute("wordList", wordList);
	    
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/wordList.jsp");
		dispatcher.forward(request,response);	
	}
}