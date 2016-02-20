package com.prrknh.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
    
    /**
     *  指定月リストへ
     *  req : date(指定月、形式："2016年02月")
     *  
     *  res : date(指定月、形式："2016年02月")
     *  	  wordList(指定月のList<GoogledWord>のワードリスト)
     *  	  msg(更新後戻ってきた時のメッセージ nullable)
     */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック/パラムチェック
		if (req.getSession().getAttribute("userMaster") == null || req.getParameter("date") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;	
		}
		req.setCharacterEncoding("UTF-8");
		
		UserMaster userMaster = (UserMaster)req.getSession().getAttribute("userMaster");
		String strDate = req.getParameter("date");
		String dbDate = null;
		try {
			dbDate = GoogledWordUtils.dateReformat(strDate);
		} catch (ParseException e) {
			e.printStackTrace();	
		}
		GoogledWordListDao dao = new GoogledWordListDao();
		List<GoogledWord> wordList = dao.findMonthList(userMaster, dbDate);
		req.setAttribute("strDate", strDate);
		req.setAttribute("wordList", wordList);
		
		// 更新後に戻ってきた時用のメッセージ設定
		String msg = req.getParameter("msg");
		if (StringUtils.isNotEmpty(msg)) {
			req.setAttribute("msg", msg);
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/wordList.jsp");
		dispatcher.forward(req,res);
	}

	/**
	 *  ワード新規追加/編集or削除から当月リストへ
	 *  req : addWord(新規追加のワード)
	 *        memo(新規追加ワードへのメモ　nullable)
	 *        tagIdList(新規追加ワードへのタグID(s) nullable)
	 *        newTag(新規追加ワードへの新規追加タグネーム nullable)
	 *        
	 *  res : addedWord(新規追加の<GoogledWord>のワード nullable)
	 *        wordList(当月ワードリストList<GoogeldWord>)
	 *        msg(編集・削除後のメッセージを引き渡す nullable) 
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ログインチェック
		if (req.getSession().getAttribute("userMaster") == null){
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		req.setCharacterEncoding("UTF-8");
		
		UserMaster userMaster = (UserMaster)req.getSession().getAttribute("userMaster");
		GoogledWordListDao gDao =new GoogledWordListDao();
		RelTagWordDao rDao = new RelTagWordDao();
		TagMasterDao tDao = new TagMasterDao();
		
		// 新規ワード追加の場合
		if (StringUtils.isNotEmpty(req.getParameter("addWord"))){
			// ワード登録処理
			String addWord = req.getParameter("addWord");
			String memo = req.getParameter("memo");
			int addedId = gDao.addWord(userMaster, addWord, memo);
			GoogledWord addedWord = gDao.findDetail(addedId);
			req.setAttribute("addedWord", addedWord);
			
			//　タグ追加処理
			List<Integer> tagIdList = new ArrayList<>();
			tagIdList = GoogledWordUtils.returnTagIdList(req);
			if (StringUtils.isNotEmpty(req.getParameter("newTag"))){
				int tagId = tDao.createTag(userMaster, req.getParameter("newTag"));
				tagIdList.add(tagId);
			}
			if (CollectionUtils.isNotEmpty(tagIdList)){
				rDao.setTagOnWord(addedId,tagIdList);
			}
		// ワード編集/削除の場合
		} else if (StringUtils.isNotEmpty(req.getParameter("id"))){
			if (StringUtils.isNotEmpty(req.getParameter("msg"))){
				req.setAttribute("msg", req.getParameter("msg"));
			}
		} else {
			res.sendRedirect(CheckUtils.TOP_PAGE_URL);
			return;
		}
		
		// 追加/編集したものも含めて該当月リスト取得
		List<GoogledWord> wordList = gDao.findNowMonthView(userMaster);
		req.setAttribute("wordList", wordList);
	    
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/wordList.jsp");
		dispatcher.forward(req,res);
	}
}