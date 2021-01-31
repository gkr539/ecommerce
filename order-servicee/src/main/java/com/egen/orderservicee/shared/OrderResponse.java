package com.egen.orderservicee.shared;

import com.egen.orderservicee.entity.Order;

import lombok.Data;

@Data
public class OrderResponse {
	private Order order;
	private String transactionId;
	private double amount;
	private String message;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public OrderResponse(Order order, String transactionId, double amount, String message) {
		super();
		this.order = order;
		this.transactionId = transactionId;
		this.amount = amount;
		this.message = message;
	}
	
	
}
