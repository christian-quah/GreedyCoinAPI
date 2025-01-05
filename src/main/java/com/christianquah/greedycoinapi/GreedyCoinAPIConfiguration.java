package com.christianquah.greedycoinapi;

import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import jakarta.validation.constraints.*;

public class GreedyCoinAPIConfiguration extends Configuration {
    // TODO: implement service configuration
    @NotNull
    private String allowedOrigins;

    public String getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
	
}
