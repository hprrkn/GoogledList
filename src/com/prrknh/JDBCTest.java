package com.prrknh;

import java.sql.*;

public class JDBCTest {
  public static void main(String[] args) {
    try {
      
      Class.forName("org.postgresql.Driver");	// 繝峨Λ繧､繝舌け繝ｩ繧ｹ繧偵Ο繝ｼ繝�
      Connection con =
        DriverManager.getConnection("jdbc:postgresql://127.0.0.1/32:5432/testdb",	// 繝��繧ｿ繝吶�繧ｹ蜷�
                                    "testuser",					// 謗･邯壹Θ繝ｼ繧ｶ蜷�
                                    "1234");						// 繝代せ繝ｯ繝ｼ繝峨�縺ｪ縺�
      Statement stmt = con.createStatement();
      String sql = "SELECT * FROM searchhistory";		// SQL譁�ｒ謖�ｮ�
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        int id = rs.getInt("id");		// class繧貞叙蠕�
        String searchword = rs.getString("searchword");		// name繧貞叙蠕�
        String memo = rs.getString("memo");		// club繧貞叙蠕�
        System.out.println(id + " " + searchword + " " + memo);
      }
      stmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
