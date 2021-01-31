package com.egen.orderservicee.entity;

public enum OrderStatus {
	CREATED, SUCCESS, REFUND, FAILED, CANCELLED;
	
	public String getStatus() {
		return this.name();
	}
}
