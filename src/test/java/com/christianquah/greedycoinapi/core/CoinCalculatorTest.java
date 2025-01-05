package com.christianquah.greedycoinapi.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CoinCalculatorTest {
	
	CoinCalculator coinCalculator = new CoinCalculator();


    @Test
    public void testCalculateMinimumCoins_ValidCase1() {
        List<Double> result = coinCalculator.calculateMinimumCoins(7.03, new ArrayList<>(List.of(0.01, 0.5, 1.0, 5.0, 10.0)));
        assertEquals(List.of(0.01, 0.01, 0.01, 1.0, 1.0, 5.0), result);
    }

    @Test
    public void testCalculateMinimumCoins_ValidCase2() {
        List<Double> result = coinCalculator.calculateMinimumCoins(103.0, new ArrayList<>(List.of(1.0, 2.0, 50.0)));
        assertEquals(List.of(1.0, 2.0, 50.0, 50.0), result);
    }

    @Test
    public void testCalculateMinimumCoins_InvalidTarget() {
        List<Double> result = coinCalculator.calculateMinimumCoins(-5.0, new ArrayList<>(List.of(1.0, 2.0, 50.0)));
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCalculateMinimumCoins_UnachievableTarget() {
        List<Double> result = coinCalculator.calculateMinimumCoins(7.03, new ArrayList<>(List.of(5.0, 10.0)));
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCalculateMinimumCoins_EmptyDenominations() {
        List<Double> result = coinCalculator.calculateMinimumCoins(7.03, new ArrayList<>(List.of()));
        assertTrue(result.isEmpty());
    }

}
