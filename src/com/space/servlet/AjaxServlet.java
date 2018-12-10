package com.space.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.space.dao.UserDao;
import com.space.entity.User;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/login/ajaxServlet")
public class AjaxServlet extends HttpServlet {
	private UserDao userDao ;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("ajax 后台数据：");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		
		//2. 数据库数据 校验
		String isLogin = userDao.isLogin(username, password);
		System.out.println("是否登录："+isLogin);
		
		//3. 如果登录成功，即用户名及密码正确
		//	将用户信息保存到session中
		if("success".equals(isLogin)){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
		}
		
		//4. 使用JSON格式 返回数据结果
		PrintWriter printWriter = response.getWriter();
		//使用GSON  将数据转换为JSON数据格式
		Gson gson = new Gson();
		
		ArrayList<User> arrayList = new ArrayList<>();
		User user = new User() ;
		user.setUsername(username);
		user.setPassword(password);
		arrayList.add(user);
		
		//使用map返回数据如 验证成功或者失败
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("isLogin", isLogin); //是否登录成功
		hashMap.put("user", arrayList);
		
		//将arraylist数组类型 转换为 json格式
		String json = gson.toJson(hashMap);
		System.out.println("json数据："+json);
		//返回结果
		printWriter.write(json);
						
	}

}
