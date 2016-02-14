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
import com.prrknh.logic.CheckUtils;

@WebServlet("/WordListServlet")
public class WordListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WordListServlet() {
        super();
    }
    
    // 指定月リストへ
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック
		UserMaster userMaster = CheckUtils.loginCheck(req, res);
		req.setCharacterEncoding("UTF-8");
		
		// 更新後に戻ってきた時用のメッセージ設定
		String msg = req.getParameter("msg");
		if (StringUtils.isNotEmpty(msg)) {
			req.setAttribute("msg", msg);
		}
		
		String strDate = CheckUtils.getParamChecker(req, res, "date");
		GoogledWordListDao dao = new GoogledWordListDao();

		// 直接URL叩かれた時用
		if (strDate == null){
			res.sendRedirect("/GoogledList/TopPageServlet");
		}
		List<GoogledWord> wordList = dao.findMonthList(userMaster, strDate);
		req.setAttribute("strDate", strDate);
		req.setAttribute("wordList", wordList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/wordList.jsp");
		dispatcher.forward(req,res);
	}

	// ワード追加/編集で該当月リストへ
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック
		UserMaster userMaster = CheckUtils.loginCheck(req, res);
		req.setCharacterEncoding("UTF-8");
		
		GoogledWordListDao gDao =new GoogledWordListDao();
		RelTagWordDao rDao = new RelTagWordDao();
		TagMasterDao tDao = new TagMasterDao();
		
		// 新規ワード追加の場合
		if (StringUtils.isNotEmpty(req.getParameter("addWord"))){
			// formからの受け取り
			String addWord = req.getParameter("addWord");
			String memo = req.getParameter("memo");
			List<Integer> tagIdList = new ArrayList<>();
			
			// 配列からlist、list<String>からlist<Integer>という無駄な変換の嵐　直す :TODO
			if (StringUtils.isNoneEmpty(req.getParameterValues("tagId"))){
				for (String strTagId : Arrays.asList(req.getParameterValues("tagId"))){
					tagIdList.add(Integer.parseInt(strTagId));
				}
			}
			
			// 新規タグ追加
			if (StringUtils.isNotEmpty(req.getParameter("newTag"))){
				int tagId = tDao.createTag(userMaster, req.getParameter("newTag"));
				tagIdList.add(tagId);
			}
			
			int addedId = gDao.addWord(userMaster, addWord, memo);
			String strDate = GoogledWordUtils.dateFormat(gDao.findDetail(addedId).getAdded_day());
			req.setAttribute("strDate",strDate);
		    // wordとtagのinsert
			if (CollectionUtils.isNotEmpty(tagIdList)){
				rDao.setTagOnWord(addedId,tagIdList);
			}
			req.setAttribute("addWord", addWord);
		    req.setAttribute("memo", memo);
		}
		
		// ワード編集/削除の場合
		if (StringUtils.isNotEmpty(req.getParameter("id"))){
			String strDate = GoogledWordUtils.dateFormat(gDao.findDetail(Integer.parseInt(req.getParameter("id"))).getAdded_day());
			req.setAttribute("strDate",strDate);
			if (StringUtils.isNotEmpty(req.getParameter("msg"))){
				req.setAttribute("msg", req.getParameter("msg"));
			}
		}
		
		// 追加/編集したものも含めて該当月リスト取得
		List<GoogledWord> wordList = gDao.findNowMonthView(userMaster);
		req.setAttribute("wordList", wordList);
	    
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/wordList.jsp");
		dispatcher.forward(req,res);
	}
}