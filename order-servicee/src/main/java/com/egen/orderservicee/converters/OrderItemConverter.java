package com.egen.orderservicee.converters;

import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.egen.orderservicee.entity.OrderItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class OrderItemConverter  implements AttributeConverter<List<OrderItem>, String> {
	@Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<OrderItem> item) {
        try {
            return objectMapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert OrderItem object to String");
        }
    }

    @Override
    public List<OrderItem> convertToEntityAttribute(String item) {
        try {
            return objectMapper.readValue(item, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert String to OrderItem object");
        }
    }  

}
