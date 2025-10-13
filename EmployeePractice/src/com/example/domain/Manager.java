package com.example.domain;

import java.util.ArrayList;

public class Manager extends Employee implements RegularStaff{
	private String deptName;
	protected ArrayList<Employee> employees = new ArrayList<>();
	private double baseBonus = 100000;

	public Manager(String name, String ssn, double salary, String deptName) {
		super(name, ssn, salary);
		this.deptName = deptName;
	}

	public String getDeptName() {
		return deptName;
	}	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("deptName: "+ this.deptName+"\n");
		sb.append(printStaffDetails());
		return sb.toString();
	}

//	@Override
//	public void displayInformation() {
//		// TODO Auto-generated method stub
//		super.displayInformation();
//		System.out.println("dept name: "+ this.deptName);
//		printStaffDetails();
//	}
	
	@Override
	public boolean addEmployee(Employee e) {
		if (!employees.contains(e)) {
			employees.add(e);
			return true;
		}else
			return false;
	}
	
	public boolean removeEmployee(Employee e) {
		if (employees.contains(e)) {
			employees.remove(e);
			return true;
		}else
			return false;
	}
	
	public String printStaffDetails() {
		String temp ="";
		if(!employees.isEmpty()) {
			for(Object obj : employees) {
				if(obj instanceof Employee) {  //此步驟算是強轉時的safety measure: 確認obj強轉時不會出現classcastException. 
					Employee e = (Employee)obj;  //把遍歷出來的obj強轉成Employee來調用Employee的getName, getEmpId方法
					temp = "subordinates: \n" + "name: "+ e.getName()+", ID: "+ e.getEmpId();	
				}
			}
//			for(int i = 0; i < employees.size(); i++) {			
//				System.out.println("name: "+ employees.get(i).getName()+", ID: "+ employees.get(i).getEmpId());
//			}
		}
		temp = temp + "\n";
		return temp;
	}

	@Override
	public double getPay() {
		return this.getSalary()+employees.size()*2000;
	}
	
	@Override
	public double getBonus() {
		// TODO Auto-generated method stub
		return this.baseBonus*this.calcPerMultiplier();
	}
}
