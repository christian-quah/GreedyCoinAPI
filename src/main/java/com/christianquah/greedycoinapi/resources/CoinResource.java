package com.christianquah.greedycoinapi.resources;

import com.christianquah.greedycoinapi.api.*;
import com.christianquah.greedycoinapi.core.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Path("/api/coins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CoinResource {

	private final CoinCalculator coinCalculator;
	
    private static final Set<Double> ALLOWED_DENOMINATIONS = Set.of(
            0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0
        );
	
	public CoinResource (CoinCalculator coinCalculator) {
		this.coinCalculator = coinCalculator;
	}
	
	@GET
	@Path("/")
	public List<Double >getDenominations() {
		return Arrays.asList(0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0);
		//return Response.ok(new CoinResponse(null,"Allowed denominations are:" + new ArrayList<>(ALLOWED_DENOMINATIONS).stream().sorted().toList()))
		//		.build();
	}
	
	@POST
	@Path("/calculate")
	public Response calculateMinimumCoins(CoinRequest request) {
        double targetAmount = request.getTargetAmount();
        List<Double> coinDenominations = request.getCoinDenominations();
		
        // Case 1: Validate the target amount range
        if (targetAmount < 0 || targetAmount > 10000) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CoinResponse(null, "Target amount must be between 0 and 10,000."))
                    .build();
        }

        // Case 2: Validate the coin denominations
        if (!ALLOWED_DENOMINATIONS.containsAll(coinDenominations) || coinDenominations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CoinResponse(null, "Invalid coin denominations provided. Allowed denominations are: " + new ArrayList<>(ALLOWED_DENOMINATIONS).stream().sorted().toList()))
                    .build();
        }

        // Case 3: Valid input, calculate coins
        List<Double> result = coinCalculator.calculateMinimumCoins(targetAmount, coinDenominations);

        if (result.isEmpty()) {
            // Case 3a: No solution exists
            return Response.status(Response.Status.OK) // Use HTTP 200 to indicate valid input but no result
                    .entity(new CoinResponse(null, "No solution exists for the given target amount and coin denominations."))
                    .build();
        }
		
		return Response.ok(new CoinResponse(result, "Success")).build();
	}
}
