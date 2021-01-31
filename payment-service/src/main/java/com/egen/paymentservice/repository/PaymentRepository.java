package com.egen.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egen.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByorderId(String orderId);
}
