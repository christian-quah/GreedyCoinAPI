package com.christianquah.greedycoinapi.api;

import java.util.List;


public class CoinResponse {
	private List<Double> coins;
	private String message;
	
	public CoinResponse(List<Double> coins, String message) {
		this.coins= coins;
		this.message = message;
	}
	
	public List<Double> getCoins() {
		return coins;
	}
	
	public void setCoins(List<Double> coins) {
		this.coins = coins;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
