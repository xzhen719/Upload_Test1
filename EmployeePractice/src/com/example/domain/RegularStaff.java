package com.example.domain;
import java.util.Random;

public interface RegularStaff {
	String[] gifts = {"i17", "Benz", "Dyson", "Coupon", "Tesla", "Beats"};
	public static String getLuckDraw() {
		int idx = new Random().nextInt(gifts.length);
		return gifts[idx];	
	}
	public default double calcPerMultiplier() {
		return (int)(Math.random()*5+1)*0.5; //0.5, 1, 1.5, 2, 2.5
	}
	
	public double getBonus();

}
