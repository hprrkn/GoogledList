package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.prrknh.entity.GoogledWord;
import com.prrknh.entity.TagMaster;
import com.prrknh.entity.UserMaster;

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
			String sql = ResourceBundle.getBundle("TagMaster").getString("get_tag_list");			
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
	
	public List<TagMaster>getAllTagList(UserMaster usermaster){
		Connection conn = null;
		List<TagMaster> allTagList = new ArrayList<>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("TagMaster").getString("get_all_tag_list");			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, usermaster.getUserId());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				TagMaster tagMaster = new TagMaster(rs.getInt("tag_id"), rs.getString("tag_name"), rs.getInt("cnt"));
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
	
	public TagMaster getTagDetail(int tagId){
		Connection conn = null;
		TagMaster tagDetail = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("TagMaster").getString("get_tag_detail");			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, tagId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				 tagDetail = new TagMaster(rs.getInt("tag_id"), rs.getString("tag_name"));
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
		return tagDetail;
	}
	
	public void deleteTag(int tagId){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("TagMaster").getString("delete_tag");			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, tagId);
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
	
	public int createTag(UserMaster userMaster, String newTagName){
		Connection conn = null;
		int tagId = 0;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("TagMaster").getString("create_tag");			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, newTagName);
			pStmt.setInt(2, userMaster.getUserId());
			ResultSet rs =pStmt.executeQuery();
			while(rs.next()){
				tagId = rs.getInt("tag_id");
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
		return tagId;
	}
	
	public String editTag(int tagId, String editTagName){
		Connection conn = null;
		String editedTagName = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("TagMaster").getString("edit_tag");			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, editTagName);
			pStmt.setInt(2, tagId);
			ResultSet rs =pStmt.executeQuery();
			while(rs.next()){
				editedTagName = rs.getString("tag_name");
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
		return editedTagName;
	}
	
}
