package com.egen.orderservicee.entity;


public class ShippingDetails {
	private String shippingStatus;
	private String shippingType;
	@Override
	public String toString() {
		return "ShippingDetails [shippingStatus=" + shippingStatus + ", shippingType=" + shippingType + "]";
	}
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public ShippingDetails(String shippingStatus, String shippingType) {
		super();
		this.shippingStatus = shippingStatus;
		this.shippingType = shippingType;
	}
	public ShippingDetails() {
		super();
	}
	
	
	
	
}
