package com.egen.paymentservice.serviceImpl;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egen.paymentservice.entity.Payment;
import com.egen.paymentservice.repository.PaymentRepository;
import com.egen.paymentservice.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	private Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Override
	public Payment savePayment(Payment payment) throws JsonProcessingException {
		payment.setStatus(paymentCheck());
		payment.setTransactionId(UUID.randomUUID().toString());
		log.info("paymentServiceImpl request: {}", new ObjectMapper().writeValueAsString(payment));
		return paymentRepository.save(payment);
	}

	@Override
	public String paymentCheck() {
		//3rd party payment service
		return new Random().nextBoolean() ? "Success" : "Failed";
	}

	@Override
	public Payment getPaymentByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return paymentRepository.findByorderId(orderId);
	}

}
