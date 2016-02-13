package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.prrknh.entity.UserMaster;

public class UsersDao {
	private final String DRIVER_NAME = "org.postgresql.Driver";
	private final String URL ="jdbc:postgresql://127.0.0.1:5432/testdb";
	private final String DB_USER="testuser";
	private final String DB_PASS="1234";
	
	public UserMaster getUserInfo(String paramName){
		Connection conn = null;
		UserMaster userMaster = new UserMaster();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("users").getString("get_user_info");			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,paramName);
			ResultSet rs = pStmt.executeQuery();		
			while(rs.next()){
				userMaster.setUserId(rs.getInt("user_id"));
				userMaster.setUserName(rs.getString("user_name"));
				userMaster.setUserPass(rs.getString("pw"));
				userMaster.setRegisterDate(rs.getDate("registered").toString());
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
		return userMaster;
	}
	
	public boolean register(UserMaster registerUser){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
			String sql = ResourceBundle.getBundle("users").getString("register_user");			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, registerUser.getUserName());
			pStmt.setString(2, registerUser.getUserPass());
			int result = pStmt.executeUpdate();
			if(result != 1) return false; 
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
}
