package com.egen.orderservicee.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.egen.orderservicee.entity.ShippingDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class ShippingDetailsConverter implements AttributeConverter<ShippingDetails, String> {
	@Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(ShippingDetails shippingDetails) {
        try {
            return objectMapper.writeValueAsString(shippingDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert Address object to String");
        }
    }

    @Override
    public ShippingDetails convertToEntityAttribute(String shippingDetails) {
        try {
            return objectMapper.readValue(shippingDetails, ShippingDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert String to Address object");
        }
    }

}
