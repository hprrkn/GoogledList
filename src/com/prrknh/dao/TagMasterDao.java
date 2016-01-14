package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.TagMaster;

public class TagMasterDao {
	private final String DRIVER_NAME = "org.postgresql.Driver";
	private final String URL ="jdbc:postgresql://127.0.0.1:5432/testdb";
	private final String DB_USER="testuser";
	private final String DB_PASS="1234";
	
	
	public List<TagMaster>getTagList(GoogledWord gw){
		Connection conn = null;
		List<TagMaster> tagList = new ArrayList<>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT tm.tag_id, tm.tag_name FROM Searchhistory sh, rel_tag_word rtw, tagMaster tm WHERE sh.id = ? AND rtw.id = sh.id AND tm.tag_id = rtw.tag_id;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, gw.getId());
			ResultSet rs = pStmt.executeQuery();		
			while(rs.next()){
				TagMaster tagMaster = new TagMaster(rs.getInt("tag_id"), rs.getString("tag_name"));
				tagList.add(tagMaster);
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
		return tagList;
	}
	
	public List<TagMaster>getAllTagList(){
		Connection conn = null;
		List<TagMaster> allTagList = new ArrayList<>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM tagMaster";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				TagMaster tagMaster = new TagMaster(rs.getInt("tag_id"), rs.getString("tag_name"));
				allTagList.add(tagMaster);
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
		return allTagList;
	}
	
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
	
	public List<TagMaster>getCheckedAllTagList(GoogledWord wordId){
		Connection conn = null;
		List<TagMaster> allTagList = new ArrayList<>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM tagMaster";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				TagMaster tagMaster = new TagMaster(rs.getInt("tag_id"), rs.getString("tag_name"));
				allTagList.add(tagMaster);
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
		return allTagList;
	}

}
