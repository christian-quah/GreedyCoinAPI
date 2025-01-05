package com.christianquah.greedycoinapi.core;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoubleListDeserializer extends JsonDeserializer<List<Double>> {

    @Override
    public List<Double> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        List<Double> result = new ArrayList<>();
        // Read the JSON array
        for (Object value : parser.readValueAs(ArrayList.class)) {
            try {
                result.add(Double.parseDouble(value.toString()));
            } catch (NumberFormatException e) {
                throw new IOException("Invalid number format in coin denominations: " + value, e);
            }
        }
        return result;
    }
}