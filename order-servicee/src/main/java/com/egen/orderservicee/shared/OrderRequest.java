package com.egen.orderservicee.shared;

import com.egen.orderservicee.entity.Order;

import lombok.Data;

@Data
public class OrderRequest {
	private Order order;
	private Payment payment;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "OrderRequest [order=" + order + ", payment=" + payment + "]";
	}
	public OrderRequest(Order order, Payment payment) {
		super();
		this.order = order;
		this.payment = payment;
	}
	public OrderRequest() {
		super();
	}
	
	
}
