package com.prrknh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			String sql = "SELECT * FROM users WHERE activation = true AND user_name = '" + paramName + "';";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();		
			while(rs.next()){
				userMaster.setUserId(rs.getInt("user_id"));
				userMaster.setUserName(rs.getString("user_name"));
				userMaster.setUserPass(rs.getString("pw"));
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
}
