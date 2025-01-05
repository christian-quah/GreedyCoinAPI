package com.christianquah.greedycoinapi.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoinCalculator {
	public List<Double> calculateMinimumCoins(double targetAmount, List<Double> coinDenominations) {
		List<Double> result = new ArrayList<>();
		
		Collections.sort(coinDenominations, Collections.reverseOrder());
		
		for (double denom : coinDenominations) {
			while(targetAmount >= denom ) {
				targetAmount = Math.round((targetAmount-denom) * 100.0) / 100.0;
				result.add(denom);
			}
		}
		
		if(Math.round((targetAmount) * 100.0) / 100.0 > 0) {
			System.out.println(targetAmount);
			return new ArrayList<>();
		}
		
		Collections.sort(result);
		return result;
	}
}
