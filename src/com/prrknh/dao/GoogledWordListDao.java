package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.UserMaster;

public class GoogledWordListDao {
	private final String DRIVER_NAME = "org.postgresql.Driver";
	private final String URL ="jdbc:postgresql://127.0.0.1:5432/testdb";
	private final String DB_USER="testuser";
	private final String DB_PASS="1234";
	
	public List<GoogledWord>findAll(UserMaster userMaster){
		Connection conn = null;
		List<GoogledWord> wordList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("find_all");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			ResultSet rs = pStmt.executeQuery();		
			while(rs.next()){
				GoogledWord googledword = new GoogledWord(rs.getInt("id"), rs.getString("word"), rs.getString("memo"), rs.getDate("added_day"));
				wordList.add(googledword);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return wordList;
	}
	
	public Map<Date,Integer> countAllMonthWord(UserMaster userMaster){
		Connection conn = null;
		Map<Date, Integer> countMap = new LinkedHashMap<Date, Integer>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("get_cnt_word");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				countMap.put(rs.getDate("date"),(rs.getInt("count")));
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return countMap;
	}
	
	public int addWord(UserMaster userMaster, String addWord, String memo){
		Connection conn = null;
		int addedId = 0;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("add_word");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			pStmt.setString(2, addWord);
			pStmt.setString(3, memo);
			ResultSet rs =pStmt.executeQuery();
			while(rs.next()){
				addedId = rs.getInt("id");
			}
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}finally{
			if (conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return addedId;
	}
	
	public List<GoogledWord> findNowMonthView(UserMaster userMaster){
		Connection conn = null;
		List<GoogledWord> monthList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("find_now_month_view");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				GoogledWord googledword = new GoogledWord(rs.getInt("id"), rs.getString("word"), rs.getString("memo"), rs.getDate("added_day"));
				monthList.add(googledword);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return monthList;
	}
	
	public List<GoogledWord> findMonthList(UserMaster userMaster, String strDate){
		Connection conn = null;
		List<GoogledWord> monthList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("find_month_list");			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			pStmt.setString(2, strDate);
			pStmt.setString(3, strDate);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				GoogledWord googledword = new GoogledWord(rs.getInt("id"), rs.getString("word"), rs.getString("memo"), rs.getDate("added_day"));
				monthList.add(googledword);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return monthList;
	}
	
	public GoogledWord findDetail(int selectedId){
		Connection conn = null;
		GoogledWord googledWord = new GoogledWord();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("find_detail");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, selectedId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				googledWord = new GoogledWord(rs.getInt("id"), rs.getString("word"), rs.getString("memo"), rs.getDate("added_day"));
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return googledWord;
	}
	
	public void updateDetail(int id, String EditedWord, String EditedMemo){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("update_detail");			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, EditedWord);
			pStmt.setString(2, EditedMemo);
			pStmt.setInt(3, id);
			pStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deleteDetail(int id){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("delete_detail");			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<GoogledWord> findWordListByTag(UserMaster userMaster, int tagId){
		Connection conn = null;
		List<GoogledWord> wordList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("find_word_list_by_tag");			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			pStmt.setInt(2, tagId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				GoogledWord googledword = new GoogledWord(rs.getInt("id"), rs.getString("word"), rs.getString("memo"), rs.getDate("added_day"));
				wordList.add(googledword);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return wordList;
	}
	
	public int getCountByUser(UserMaster userMaster){
		Connection conn = null;
		int count = 0;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("GoogledWordList").getString("get_count_by_user");			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	
}
