package com.space.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.space.dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        userDao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 获取表单数据
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("ajax 后台数据："+username);
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		
		PrintWriter printWriter = response.getWriter();
		//2. 连接数据库， 获取数据信息并判断
		if("exist".equals(userDao.isUsernameExist(username))){ //判断用户名是否存在
			printWriter.write("username is exits");
		}else{
			//用户名不存在，可以注册
			int register = userDao.isRegister(username, password);
			if(register == 0){
				printWriter.write("注册失败");
			}else{
				printWriter.write("注册成功");
			}
		}
	}

}
