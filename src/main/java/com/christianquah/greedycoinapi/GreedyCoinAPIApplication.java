package com.christianquah.greedycoinapi;
import com.christianquah.greedycoinapi.resources.*;

import java.util.EnumSet;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.christianquah.greedycoinapi.core.*;

import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GreedyCoinAPIApplication extends Application<GreedyCoinAPIConfiguration> {
	 private static final Logger LOGGER = LoggerFactory.getLogger(GreedyCoinAPIApplication.class);

    public static void main(final String[] args) throws Exception {
        new GreedyCoinAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "GreedyCoinAPI";
    }

    @Override
    public void initialize(final Bootstrap<GreedyCoinAPIConfiguration> bootstrap) {
        // TODO: application initialization
    	bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(final GreedyCoinAPIConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    	LOGGER.info("Allowed Origins: {}", configuration.getAllowedOrigins());
        final FilterRegistration.Dynamic cors = environment.servlets()
                .addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", configuration.getAllowedOrigins());
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter("allowCredentials", "true");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Register resources
        CoinCalculator coinCalculator = new CoinCalculator();
        environment.jersey().register(new CoinResource(coinCalculator));
    }

}
