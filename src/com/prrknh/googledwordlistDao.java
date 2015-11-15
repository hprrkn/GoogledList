package com.prrknh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class googledwordlistDao {
	private final String DRIVER_NAME = "org.postgresql.Driver";
	private final String URL ="jdbc:postgresql://127.0.0.1:5432/testdb";
	private final String DB_USER="testuser";
	private final String DB_PASS="1234";

	public List<GoogledWord>findAll(){
		Connection conn = null;
		List<GoogledWord> wordList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM searchhistory;";
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
	
	public boolean addWord(String addWord, String memo){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "INSERT INTO searchhistory(word,memo) VALUES('" + addWord + "','" + memo + "');";
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
	
	public List<GoogledWord> findNowMonthView(){
		Connection conn = null;
		List<GoogledWord> monthList = new ArrayList<GoogledWord>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM searchhistory WHERE added_day >= DATE_TRUNC('month'::text, now())::date AND added_day <= (DATE_TRUNC('month', now() + '1 month'::interval) + '-1 days'::interval)::date";
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
}
