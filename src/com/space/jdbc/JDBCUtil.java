package com.space.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author space
 *	
 */
//获取jdbc 连接
public class JDBCUtil {
	private Connection conn;
	
	private final String Driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/myblog";
	private final String username = "root" ;
	private final String password = "123456" ;
	
	public Connection getConnection(){
		
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
