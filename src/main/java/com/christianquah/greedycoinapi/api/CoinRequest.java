package com.christianquah.greedycoinapi.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.christianquah.greedycoinapi.core.DoubleListDeserializer;

import java.util.List;

public class CoinRequest {
	@JsonProperty("targetAmount")
    private double targetAmount;
	
	@JsonProperty("coinDenominations")
    private List<Double> coinDenominations;

    // Getters and Setters
    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public List<Double> getCoinDenominations() {
        return coinDenominations;
    }

    public void setCoinDenominations(List<Double> coinDenominations) {
        this.coinDenominations = coinDenominations;
    }
}
