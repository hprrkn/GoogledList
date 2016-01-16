package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RelTagWordDao {
		private final String DRIVER_NAME = "org.postgresql.Driver";
		private final String URL ="jdbc:postgresql://127.0.0.1:5432/testdb";
		private final String DB_USER="testuser";
		private final String DB_PASS="1234";

		public void setTagOnWord(int wordId, List<Integer> tagIdList){
			Connection conn = null;
			try{
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
				String sql = "INSERT INTO rel_tag_word(id, tag_id) VALUES (?, ?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				for(Integer tagId : tagIdList){
					pStmt.setInt(1, wordId);
					pStmt.setInt(2, tagId);
					pStmt.executeUpdate();
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
		}
		
		public void deleteTag(int wordId){
			Connection conn = null;
			try{
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
				String sql = "update rel_tag_word activation = f WHERE id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, wordId);
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
