package com.egen.orderservicee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egen.orderservicee.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
