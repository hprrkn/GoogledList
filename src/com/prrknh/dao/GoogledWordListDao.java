package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			String sql = "SELECT * FROM searchhistory WHERE activation = t AND user_id = '" + userMaster.getUserId() + "';";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();		
			while(rs.next()){
				int id = rs.getInt("id");
				String word = rs.getString("word");
				String memo = rs.getString("memo");
				Date added_day = rs.getDate("added_day");
				GoogledWord googledword = new GoogledWord(id, word, memo, added_day);
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
	
	public boolean addWord(UserMaster userMaster, String addWord, String memo){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "INSERT INTO searchhistory(user_id, word,memo) VALUES('" +userMaster.getUserId() + "','" + addWord + "','" + memo + "');";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
			String sql = "SELECT * FROM searchhistory WHERE WHERE activation = t AND added_day >= DATE_TRUNC('month', now())::date AND added_day < (DATE_TRUNC('month', now() + '1 month'))::date";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String word = rs.getString("word");
				String memo = rs.getString("memo");
				Date added_day = rs.getDate("added_day");
				GoogledWord googledword = new GoogledWord(id, word, memo, added_day);
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
			String sql = "SELECT * FROM searchhistory WHERE WHERE user_id = " + "'" + userMaster.getUserId() + "'" + "AND activation = t AND added_day >= '" + year + "-" + month + " 01' AND added_day < (DATE_TRUNC('month', '" + year + "-" + month + " -01'::date + '1 month'))::date";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String word = rs.getString("word");
				String memo = rs.getString("memo");
				Date added_day = rs.getDate("added_day");
				GoogledWord googledword = new GoogledWord(id, word, memo, added_day);
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
			String sql = "SELECT * FROM searchhistory WHERE WHERE activation = t AND id = " + selectedId;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String word = rs.getString("word");
				String memo = rs.getString("memo");
				Date added_day = rs.getDate("added_day");
				googledWord = new GoogledWord(id, word, memo, added_day);
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
			String sql = "UPDATE searchhistory SET word = ' "+ EditedWord + "', memo = '"+ EditedMemo + "' WHERE id = " + id + ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
			String sql = "UPDATE searchhistory SET activation = f WHERE id = " + id + ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
