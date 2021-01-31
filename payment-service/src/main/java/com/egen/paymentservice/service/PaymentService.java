package com.egen.paymentservice.service;

import org.springframework.stereotype.Service;

import com.egen.paymentservice.entity.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface PaymentService {
	public Payment savePayment(Payment order) throws JsonProcessingException;
	public String paymentCheck();
	public Payment getPaymentByOrderId(String orderId);

}
