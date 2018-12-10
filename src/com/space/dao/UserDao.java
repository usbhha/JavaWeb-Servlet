package com.space.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.space.jdbc.JDBCUtil;

public class UserDao {
	private JDBCUtil jdbcUtil = new JDBCUtil();
	private Connection conn ;
	private PreparedStatement pstmt ;
	private ResultSet rs ;
	private int i = 0 ;
	
	public UserDao() {
		conn = jdbcUtil.getConnection();
	}
	
	//登录判断
	public String isLogin(String username ,String password){
		String sql = "select * from admin where username='"+username+"'";
		System.out.println("sql:"+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//判断用户名是否正确
			rs.last(); //将结果集 移动到最后一行 ;判断是否有数据
			if(rs.getRow() == 0){ //无数据
				return "username is not exits";
			}else{ 
				//有数据，rs结果集移动到第一个
				rs.beforeFirst();
				//判断密码是否正确
				while(rs.next()){
					if(password.equals(rs.getString("password"))){
						return "success" ;
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return "password Error";
	}
	
	//注册 ；插入数据
	public int isRegister(String username ,String password){
		String sql = "insert into admin(id,username,password) value(null,?,?)";
		int executeUpdate = 0 ;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//执行插入更新；返回int类型，表示更新的行数（受影响的行数）；无数据更新或者插入返回0
			executeUpdate = pstmt.executeUpdate(); 
			System.out.println("executeUpdate:"+executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return executeUpdate;
	}
	
	//判断是否有该用户
	public String isUsernameExist(String username){
		String sql = "select * from admin where username='"+username+"'";
		System.out.println("sql:"+sql);
	
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last(); //将结果集 移动到最后一行 ;判断是否有数据
			if(rs.getRow() == 0){
				return "noExist";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "exist";
	}
	
	//获取所有 用户信息
	public void getAllUser(){
		String sql = "select * from admin";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			System.out.println("数据字段数："+col);
			while(rs.next()){
				i++ ;
				System.out.println(i+":"+"id="+rs.getString("id")+" ; "
				+"username="+rs.getString("username")+" ; "
				+"password="+rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}
