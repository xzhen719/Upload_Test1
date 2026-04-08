package com.example.domain;

public enum Branch {
	TAIPEI("NT$"),LONDON("£"),PARIS("€"), TOKYO("¥"); 
	private String currency;
	private Branch(String currency) {
		this.currency = currency;
	}
	public String getCurrency() {
		return currency;
	}
}
