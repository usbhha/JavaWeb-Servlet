package com.space.dao;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		userDao.getAllUser();
		
		int register = userDao.isRegister("hello", "hello");
		System.out.println("¼ÆÊý£º"+register);
	}
}
