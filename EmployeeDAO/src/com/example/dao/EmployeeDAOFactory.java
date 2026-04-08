package com.example.dao;

public class EmployeeDAOFactory {
	public EmployeeDAO createEmployeeDAO() {
		//return new EmployeeDAOMemoryImpl(); //改成Map不受Array宣告數量限制
//		return new EmployeeDAOMapImpl(); //改成persistance file
//		return new EmployeeDAOFileImpl("employees.txt"); //改成DAOJDBC與資料庫進行連線
		return new EmployeeDAOJDBCImpl();
	}

}
