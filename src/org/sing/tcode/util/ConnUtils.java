package org.sing.tcode.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnUtils {
	
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=tcodedb";
	
	private static String user = "sa";
	
	private static String passwd = "sa";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void close(Connection conn,PreparedStatement ps,ResultSet rs) {
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
