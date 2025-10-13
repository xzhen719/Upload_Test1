package com.example.domain;

public class Admin extends Employee {
	private int hours = 160;

	public Admin(String name, String ssn, double salary) {
		super(name, ssn, salary);
		// TODO Auto-generated constructor stub
	}
	
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public double getPay() {
		return this.getSalary() * hours / 160;
	}

	

}
