package com.example;

public class Cat extends Animal implements Pet{

	private String name = "野貓";
	
	public Cat() {
	}
	
	public Cat(String name) {
		super(4);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void eat() {
		if(name!=null && name.length()!=0 || !name.endsWith("野貓"))
			System.out.println(name+"最喜歡吃魚");
		else
			System.out.println("貓最喜歡吃魚");			
	}

	@Override
	public void play() {
		System.out.println("和"+this.name+"玩毛線球!");
		
		
	}

}
