package com.example.dao;

import java.io.*;
import java.text.*;
import java.util.*;


import com.example.model.Employee;

public class EmployeeDAOFileImpl implements EmployeeDAO {
	private SortedMap<Integer, Employee> employees = new TreeMap<>();
	//Integer itself implements interface Comparable so datas are sorted in ascending orders.
	private SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy", Locale.US);
	private String fileName;

	public EmployeeDAOFileImpl(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	//把file的內容倒到Treemap裡
	private void syncData() throws DAOException{
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			employees.clear();
			while(br.ready()) {
				String line = br.readLine();
				if(line == null || line.length()==0) 
					continue;
				//1|Sean|Cheng|Mar 21, 1974|50000.00
				String data[] = line.split("\\|");
				try {
					int id = Integer.parseInt(data[0]);
					Date birth = df.parse(data[3]);
					float salary = Float.parseFloat(data[4]);
					Employee e = new Employee(id, data[1], data[2], birth, salary);
					employees.put(id, e);
				} catch (ParseException | NumberFormatException e) {
					System.out.println("資料轉換失敗: "+line);
				}
				
			}
		}catch(IOException e) {
			throw new DAOException("檔案讀取失敗", e);
		}
	}

	//把treemap的內容倒到file裡
	private void commit() throws DAOException{
		try(PrintWriter pw = new PrintWriter(new FileWriter(fileName))){
			Set<Integer> index = employees.keySet();
			for(Integer i : index) {
				Employee emp = employees.get(i);
				String line = String.format("%d|%s|%s|%s|%.2f", 
						emp.getId(), emp.getFirstName(), emp.getLastName(), df.format(emp.getBirthDate()), emp.getSalary());
				pw.println(line);
			}
			pw.flush();
		}catch(IOException e) {
			throw new DAOException("檔案寫出失敗!", e);
		}	
	}
	
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
		commit();  //加完要commit到persistance file

	}

	@Override
	public void update(Employee emp) throws DAOException {
		int id = emp.getId();
		if(!employees.containsKey(id)) {
			throw new DAOException("員工不存在, 建立失敗");
		}
		employees.put(id, emp);
		commit();

	}

	@Override
	public void delete(int id) throws DAOException {
		if(!employees.containsKey(id)) {
			throw new DAOException("員工不存在, 建立失敗");
		}employees.remove(id);
		commit();
	}

	@Override
	public Employee findByID(int id) throws DAOException {
		syncData(); //從file中拉出最新的Data, 再find or Get
		Employee emp = employees.get(id); //如果不存在, get方法會丟出null
		return emp;
	}

	@Override
	public Employee[] getAllEmployees() throws DAOException {
		syncData();
		return employees.values().toArray(new Employee[0]);
		//map.values() -> returns a collection view of the values present in the map.
		//拿到Employee value再轉成Employee Array.
	}

}
