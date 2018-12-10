package com.space.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.space.entity.User;
import com.space.jdbc.JDBCUtil;

/**
 * JDBC 增删改查 操作
 * @author space
 *
 */
public class BaseDao {
	private JDBCUtil jdbcUtil = new JDBCUtil();
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	private int executeUpdate = 0 ;
	
	public BaseDao() {
		conn = jdbcUtil.getConnection();
	}
	/*
	 * 查询
	 */
	public void getQuery(){
		String sql = "select * from admin";
		try {
			pstmt= (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				//获取表字段id
				String id = rs.getString("id");
				System.out.println(id);
				//获取表字段 username
				String username = rs.getString("username");
				System.out.println(username);
				//获取表字段 password 
				String password = rs.getString("password");
				System.out.println(password);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭连接
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 增加;
	 * 	表admin ； id主键自增
	 * 	执行excuteUpdate()函数返回int类型，表示受影响的行数；0表示插入失败
	 * 	
	 * 	这么处理可能会抛出异常：如username设置了 唯一索引，若重复插入将抛出异常。
	 * 	需要先进行 查询重复判断；
	 * 
	 */
	public int insert(User user){
		String sql = "insert into admin(id,username,password) value(null,?,?)";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
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
	
	/*
	 * 修改
	 */
	public int update(User user){
		String sql = "update admin set password = ? where username = ?";
		
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
			executeUpdate = pstmt.executeUpdate();
			System.out.println("executeUpdate:"+executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executeUpdate ;
	}
	
	/*
	 * 删除
	 */
	public int delete(User user){
		String sql = "delete from admin where username = ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			executeUpdate = pstmt.executeUpdate();
			System.out.println("executeUpdate:"+executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return executeUpdate;
	}
	
	
	/*
	 * 测试； 使用时，注销其他操作。避免数据混乱
	 */
	public static void main(String[] args) {
		BaseDao baseDao = new BaseDao();
		User user = new User();
		
		//1. 查询测试
		System.out.println("查询--------------------");
		baseDao.getQuery();
		
		//2. 增加
		System.out.println("插入--------------------");
		user.setUsername("hello1");
		user.setPassword("hello1");
		int insert = baseDao.insert(user);
		if(insert != 0){
			System.out.println("插入数据成功");
		}
		
		//3. 修改
		System.out.println("修改--------------------");
		user.setUsername("hello");
		user.setPassword("main");
		int update  = baseDao.update(user);
		if(update != 0){
			System.out.println("修改数据成功");
		}
		
		//4. 删除
		System.out.println("删除--------------------");
		user.setUsername("qwer");
		int delete = baseDao.delete(user);
		if(delete != 0){
			System.out.println("删除数据成功");
		}
	}
	
}
