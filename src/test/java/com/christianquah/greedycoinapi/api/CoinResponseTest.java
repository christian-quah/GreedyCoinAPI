package com.christianquah.greedycoinapi.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

class CoinResponseTest {


    @Test
    public void testCoinResponse_Success() {
        CoinResponse response = new CoinResponse(List.of(1.0, 2.0, 50.0), "Success");
        assertEquals(List.of(1.0, 2.0, 50.0), response.getCoins());
        assertEquals("Success", response.getMessage());
    }

    @Test
    public void testCoinResponse_Error() {
        CoinResponse response = new CoinResponse(null, "Error: Invalid input.");
        assertNull(response.getCoins());
        assertEquals("Error: Invalid input.", response.getMessage());
    }

}
