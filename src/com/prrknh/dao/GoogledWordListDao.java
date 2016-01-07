package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.UserMaster;
import com.sun.javafx.collections.MappingChange.Map;

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
			String sql = "SELECT * FROM searchhistory WHERE activation = true AND user_id = ?;";
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
	
	public HashMap<Date,Integer> countAllMonthWord(UserMaster userMaster){
		Connection conn = null;
		HashMap<Date, Integer> countList = new HashMap<Date, Integer>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT date_trunc('month',added_day) AS date, COUNT(id) AS count FROM searchhistory WHERE user_id = ? GROP BY date_trunc('month',added_day) ORDER BY date_trunc('month',added_day)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				countList.put(rs.getDate("date"),rs.getInt("count"));
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
		return countList;
	}
	
	public boolean addWord(UserMaster userMaster, String addWord, String memo){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "INSERT INTO searchhistory(user_id, word, memo) VALUES(?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			pStmt.setString(2, addWord);
			pStmt.setString(3, memo);
			int result = pStmt.executeUpdate();
			if (result != 1){
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally{
			if (conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public List<GoogledWord> findNowMonthView(UserMaster userMaster){
		Connection conn = null;
		List<GoogledWord> monthList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM searchhistory WHERE user_id = ? AND activation = true AND added_day >= DATE_TRUNC('month', now())::date AND added_day < (DATE_TRUNC('month', now() + '1 month'))::date";
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
	
	public List<GoogledWord> findMonthList(UserMaster userMaster, int year, int month){
		Connection conn = null;
		List<GoogledWord> monthList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM searchhistory WHERE WHERE user_id = ? AND activation = true AND added_day >= '?-?-01' AND added_day < (DATE_TRUNC('month', '?-?-01'::date + '1 month'))::date";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userMaster.getUserId());
			pStmt.setInt(2, year);
			pStmt.setInt(3, month);
			pStmt.setInt(4, year);
			pStmt.setInt(5, month);
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
			String sql = "SELECT * FROM searchhistory WHERE activation = true AND id = ?;";
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
			String sql = "UPDATE searchhistory SET word = ?, memo = ? WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
			String sql = "UPDATE searchhistory SET activation = false WHERE id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
}
