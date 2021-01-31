package com.egen.orderservicee.entity;

public enum ShippingType {
	DELIVERY, CURB_SIDE, IN_STORE ;
	
	public String getType() {
		return this.name();
	}
}
