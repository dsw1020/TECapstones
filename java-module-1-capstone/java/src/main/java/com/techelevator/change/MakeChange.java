package com.techelevator.change;

import java.util.HashMap;
import java.util.Map;


public class MakeChange {


	
	public String makeChange (double currentBalance) {
		Coin[] coins = new Coin[] { 	new Quarter(), new Dime(), new Nickel() };
		currentBalance *= 100;
		String result = "";
		
		Map<Coin, Integer> change = new HashMap<Coin, Integer>();
		
		for (Coin coin : coins) { 
			
			if (currentBalance <= 0) { break; }
			int cent = (int)currentBalance / coin.getValue();
			if (cent > 0) {
				currentBalance = currentBalance % (coin.getValue() * cent);
				change.put(coin, cent);
			}
		}
		
		for (Coin coin : change.keySet()) {
			result += (change.get(coin) + " " + coin.getName() + "(s)" + " "); 
		}
		
		return result;
	}

}
