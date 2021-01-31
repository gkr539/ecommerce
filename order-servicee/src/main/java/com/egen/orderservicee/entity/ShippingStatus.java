package com.egen.orderservicee.entity;

public enum ShippingStatus {
	CREATED, PACKED, SHIPPED, DELIVERED, FAILED;
	
	public String getStatus() {
		return this.name();
	}
}
