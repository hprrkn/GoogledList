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
			String sql = "SELECT tm.tag_id, tm.tag_name,tm.activation,rtw.activation FROM rel_tag_word rtw INNER JOIN tagMaster tm ON tm.tag_id = rtw.tag_id WHERE rtw.id = ? AND tm.activation = true AND rtw.activation = true;";
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
			String sql = "SELECT * FROM tagMaster WHERE user_id = ? AND activation = true";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, usermaster.getUserId());
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
	
	public TagMaster getTagDetail(int tagId){
		Connection conn = null;
		TagMaster tagDetail = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = "SELECT * FROM tagMaster WHERE tag_id = ? AND activation = true";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
			String sql = "UPDATE tagmaster SET activation = false WHERE tag_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
			String sql = "INSERT INTO tagMaster(tag_name,user_id) VALUES (?,?) RETURNING tag_id;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
			String sql = "UPDATE tagMaster SET tag_name = ? WHERE tag_id = ? RETURNING tag_name;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
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
