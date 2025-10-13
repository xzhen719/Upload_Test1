package com.example.domain;

public class Director extends Manager{

	private double budget;
	private double baseBonus = 500000;

	public Director(String name, String ssn, double salary, String deptName, double budget) {
		super(name, ssn, salary, deptName);
		this.budget = budget;
		
	}

	public double getBudget() {
		return budget;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("budget: "+ nf.format(this.budget));
		return sb.toString();
	}
	
	
//	@Override
//	public void displayInformation() {
//		// TODO Auto-generated method stub
//		System.out.println("budget: "+ nf.format(this.budget));
//		super.displayInformation();
//		
//	}

	@Override
	public double getPay() {
		// TODO Auto-generated method stub
		return this.getSalary()+employees.size()*10000;
	}

	@Override
	public boolean addEmployee(Employee e) {
		// TODO Auto-generated method stub
		return super.addEmployee(e);
	}
	@Override
	public double getBonus() {
		// TODO Auto-generated method stub
		return this.baseBonus*this.calcPerMultiplier();
	}
}
