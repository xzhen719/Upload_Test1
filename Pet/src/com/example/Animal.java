package com.example;

public abstract class Animal {
	protected int legs;
	protected String name;
	protected int age;
	
	protected Animal() {}
	protected Animal(int legs) {
		this.legs = legs;
	}
	
	public void walk() {
		System.out.printf("用%d隻腳走路%n", legs);
	}
	
	public abstract void eat();

}
