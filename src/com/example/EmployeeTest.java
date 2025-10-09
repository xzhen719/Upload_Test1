package com.example;

import com.example.domain.*;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee[] emps = new Employee [5];
		emps[0] = new Employee("Abby", "A123435234", 50000);
		emps[1] = new Admin("Amy", "B123456345", 70000);
		emps[2]= new Engineer("Sean", "C1234345", 55555);
		if(emps[2] instanceof Engineer) {
			Engineer eng = (Engineer)emps[2];
			eng.addSkill("Java");
			eng.addSkill("Android");
		}
		
		emps[3] = new Manager("Jessica","D123345345", 100000, "sales");
		emps[4] = new Director("Steve", "E12334234", 200000, "Global management", 5000000);
			
//		emps[0].toString();
//		emps[1].toString();
//		emps[2].toString();
		
		if(emps[3] instanceof Manager) {
			Manager m = (Manager)emps[3];
			m.addEmployee(emps[0]);
			m.addEmployee(emps[1]);
			m.addEmployee(emps[2]);
		}
//		emps[3].toString();
		
		emps[4].addEmployee(emps[3]);
//		emps[4].toString();
		
		for(Object e: emps) {
			System.out.print(String.valueOf(e));
		}
	}

}
