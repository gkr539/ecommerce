package com.egen.orderservicee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.egen.orderservicee.entity.Order;
import com.egen.orderservicee.shared.OrderRequest;
import com.egen.orderservicee.shared.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface OrderService {
	public OrderResponse saveOrder(OrderRequest order) throws JsonProcessingException;

	public String cancelOrder(Long id);

	public Order updateOrder(Long id, Order order);
	
	public Order getOrderById(Long id);

	public List<Order> getAllOrders();

}
