package com.example.dao;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.example.model.Employee;

public class EmployeeDAOMapImpl implements EmployeeDAO {
	private SortedMap<Integer, Employee> employees = new TreeMap<>();
	//Integer itself implements interface Comparable so datas are sorted in ascending orders.

	@Override
	public void close() throws Exception {
		System.out.println("資源關閉......");

	}

	@Override
	public void add(Employee emp) throws DAOException {
		int id = emp.getId();
		if(employees.containsKey(id)) {
			throw new DAOException("員工已存在, 建立失敗");
		}
		employees.put(id, emp);

	}

	@Override
	public void update(Employee emp) throws DAOException {
		int id = emp.getId();
		if(!employees.containsKey(id)) {
			throw new DAOException("員工不存在, 建立失敗");
		}
		employees.put(id, emp);

	}

	@Override
	public void delete(int id) throws DAOException {
		if(!employees.containsKey(id)) {
			throw new DAOException("員工不存在, 建立失敗");
		}employees.remove(id);
	}

	@Override
	public Employee findByID(int id) throws DAOException {
		Employee emp = employees.get(id); //如果不存在, get方法會丟出null
		return emp;
	}

	@Override
	public Employee[] getAllEmployees() throws DAOException {
		return employees.values().toArray(new Employee[0]);
		//map.values() -> returns a collection view of the values present in the map.
		//拿到Employee value再轉成Employee Array.
	}

}
