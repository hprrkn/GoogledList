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
			String sql = "SELECT * FROM searchhistory";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();		
			while(rs.next()){
				int id = rs.getInt("id");
				String word = rs.getString("searchword");
				String memo = rs.getString("memo");
				Date resister_day = rs.getDate("resister_day");
				GoogledWord googledword = new GoogledWord(id,word, memo, resister_day);
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
}
