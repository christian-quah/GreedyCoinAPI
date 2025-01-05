package com.christianquah.greedycoinapi.resources;

import com.christianquah.greedycoinapi.api.CoinRequest;
import com.christianquah.greedycoinapi.api.CoinResponse;
import com.christianquah.greedycoinapi.core.CoinCalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;



import jakarta.ws.rs.core.Response;

class CoinResourceTest {
    private final CoinCalculator coinCalculator = new CoinCalculator(); // Use real implementation
    private final CoinResource resource = new CoinResource(coinCalculator);

    @Test
    public void testInvalidTargetAmount() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(-5.0);
        request.setCoinDenominations(Arrays.asList(1.0, 2.0, 50.0));
        Response response = resource.calculateMinimumCoins(request);
        CoinResponse entity = (CoinResponse) response.getEntity();

        assertEquals(400, response.getStatus());
        assertNull(entity.getCoins());
        assertEquals("Target amount must be between 0 and 10,000.", entity.getMessage());
    }

    @Test
    public void testInvalidCoinDenominations() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(50.0);
        request.setCoinDenominations(Arrays.asList(0.03, 0.5, 1.0));
        Response response = resource.calculateMinimumCoins(request);
        CoinResponse entity = (CoinResponse) response.getEntity();

        assertEquals(400, response.getStatus());
        assertNull(entity.getCoins());
        assertEquals("Invalid coin denominations provided. Allowed denominations are: [0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0]", entity.getMessage());
    }

    @Test
    public void testValidInputNoSolution() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(7.03);
        request.setCoinDenominations(Arrays.asList(5.0, 10.0));
        Response response = resource.calculateMinimumCoins(request);
        CoinResponse entity = (CoinResponse) response.getEntity();

        assertEquals(200, response.getStatus());
        assertNull(entity.getCoins());
        assertEquals("No solution exists for the given target amount and coin denominations.", entity.getMessage());
    }

    @Test
    public void testValidInputWithSolution() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(7.03);
        request.setCoinDenominations(Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0));
        Response response = resource.calculateMinimumCoins(request);
        CoinResponse entity = (CoinResponse) response.getEntity();

        assertEquals(200, response.getStatus());
        assertEquals(List.of(0.01, 0.01, 0.01, 1.0, 1.0, 5.0), entity.getCoins());
        assertEquals("Success", entity.getMessage());
    }
}
