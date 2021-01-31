package com.egen.orderservicee.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.egen.orderservicee.converters.AddressConverter;
import com.egen.orderservicee.converters.OrderItemConverter;
import com.egen.orderservicee.converters.ShippingDetailsConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
	
	private static final long serialVersionUID = 2450543877898709516L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String order_id;
	private String name;
	private double price;
	@Convert(converter = AddressConverter.class)
	@Column(name ="billing_address")
	private Address billingAddress;
	
	@Convert(converter = AddressConverter.class)
	@Column(name ="shipping_address")
	private Address shippingAddress;
	
	@Convert(converter = OrderItemConverter.class)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@CreationTimestamp
	@Column(name ="created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	
    @UpdateTimestamp
	@Column(name ="modified_at")
	private Date modifiedAt;
	
	private String orderStatus;
	
	@Convert(converter = ShippingDetailsConverter.class)
	private ShippingDetails shippingDetails;
	
	






	public ShippingDetails getShippingDetails() {
		return shippingDetails;
	}
	public void setShippingDetails(ShippingDetails shippingDetails) {
		this.shippingDetails = shippingDetails;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public Order setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
		return this;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public Order setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
		return this;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Order setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public Order setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
		return this;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public Order setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public Order setName(String name) {
		this.name = name;
		return this;
	}
	public double getPrice() {
		return price;
	}
	public Order setPrice(double price) {
		this.price = price;
		return this;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}
	public Order setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
		return this;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", order_id=" + order_id + ", name=" + name + ", price=" + price
				+ ", billingAddress=" + billingAddress + ", shippingAddress=" + shippingAddress + ", orderItems="
				+ orderItems + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", orderStatus="
				+ orderStatus + ", shippingDetails=" + shippingDetails + "]";
	}
	
	
	
	
	
	
}
