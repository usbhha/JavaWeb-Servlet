package com.space.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.space.dao.UserDao;

/**
 * Servlet 路径方式：
 * 	1. 注解方式： @WebServlet("/LoginServlet")
 * 		但需要注意的是 web.xml文件中metadata-complete="false"；即 <web-app metadata-complete="false" >
 *  2. xml配置方式 ：
 * 
 */

/*@WebServlet("/login/loginServlet")*/
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		request.setCharacterEncoding("utf-8");
		//1. 获取表单信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		
		//2. 调用userDao,并与数据库信息进行判断，返回判断结果
		String isLogin = userDao.isLogin(username, password);
		System.out.println("是否登录："+isLogin);
		
		//3. 是否登录成功并 转发或者重定向页面
		if("success".equals(isLogin)){ //登录成功
			//帐号密码正确
			/*request.setAttribute("username", username);
			request.setAttribute("password", password);*/
			
			//将用户信息保存到session域中
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else{ //登录失败
			System.out.println("错误原因："+isLogin);
			//重定向登录界面
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
		
		
		//搭建环境测试；使用固定帐号密码实现重定向
		/*if("asdf".equals(username) && "asdf".equals(password)){
			System.out.println("帐号密码正确");
			//转发 success界面
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else{
			System.out.println("帐号或密码错误");
			//重定向登录界面
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}*/
		
		
	}

}
