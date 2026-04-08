package com.example.domain;

public class Engineer extends Employee implements RegularStaff{
	private int skillCount;
	private String[] skills;

	public Engineer(String name, String ssn, double salary, Branch branch) {
		super(name, ssn, salary, branch);
		skills = new String[5];
		skillCount = 0;
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		if(skillCount > 0) {
			sb.append("skills: ");
			for(int i = 0; i < skillCount; i++)
				sb.append(skills[i] + " ");
		}
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public void addSkill(String skill) {
		if(skillCount < skills.length) {
			skills[skillCount] = skill;
			skillCount++;
		}else {
			System.out.println("Can not add more than five skills");
		}
	}

	@Override
	public double getPay() {
		// TODO Auto-generated method stub
		return this.getSalary()+skillCount*3000;
	}
	
	@Override
	public double getBonus() {
		return this.getSalary()*this.calcPerMultiplier();
	}
}
