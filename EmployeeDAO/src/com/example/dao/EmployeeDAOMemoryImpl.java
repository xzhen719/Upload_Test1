package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Employee;
//修改為EmployeeDAOMapImpl -> 原本MemoryImpl只能存放員邊0~9, Map不受此限

public class EmployeeDAOMemoryImpl implements EmployeeDAO {
    private Employee[] employeeArray = new Employee[10];
    

	@Override
	public void add(Employee emp) throws DAOException{
		int id = emp.getId();
			try {
				if(employeeArray[id]!=null) 
					throw new DAOException("員工已存在, 建立失敗");
				employeeArray[id] = emp;
			}catch(ArrayIndexOutOfBoundsException e){
				throw new DAOException("員工編號需小於10", e);   //把catch到的exception統一轉成自訂義例外
																//e是cause, 也就是原本的exception--> ArrayIndexOutOfBoundsException
			}
	}
    //add and update has the same logic
	@Override
	public void update(Employee emp) throws DAOException{
		int id = emp.getId();
		try {
			if(employeeArray[id]==null) 
				throw new DAOException("員工不存在, 操作失敗");
			employeeArray[id] = emp;
		}catch(ArrayIndexOutOfBoundsException e){
			throw new DAOException("員工編號需小於10", e);
		}	
	}

	@Override
	public void delete(int id) throws DAOException{
		try {
			if(employeeArray[id]==null) 
				throw new DAOException("員工不存在, 操作失敗");
			employeeArray[id] = null;
		}catch(ArrayIndexOutOfBoundsException e){
			throw new DAOException("員工編號需小於10", e);
		}	
	}

	@Override
	public Employee findByID(int id) throws DAOException{
		try {
			return employeeArray[id];
		}catch(ArrayIndexOutOfBoundsException e){
			throw new DAOException("員工編號需小於10", e);
		}
	}

	@Override
	public Employee[] getAllEmployees() {
		 List<Employee> emps = new ArrayList<>();
	        // Iterate through the memory array and find Employee objects
	        for (Employee e : employeeArray) {
	            if (e != null) {
	                emps.add(e);
	            }
	        }
	        return emps.toArray(new Employee[0]); //<T> T[] toArray(T[] a); put a new limited type array as argument,
	        															//  0 is convention, JVM automatically convert to the actual length of the array list.
	}

	@Override
	public void close() throws Exception {
		System.out.println("資源關閉");
		
	}
}
