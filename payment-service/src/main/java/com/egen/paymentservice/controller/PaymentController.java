package com.egen.paymentservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egen.paymentservice.entity.Payment;
import com.egen.paymentservice.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/createPayment")
	public Payment createPayment(@RequestBody Payment payment) throws JsonProcessingException {
		
		return paymentService.savePayment(payment);
	}
	
	@GetMapping("/{orderId}")
	public Payment getPaymentDetailsByOrderId(@PathVariable String orderId) {
		return paymentService.getPaymentByOrderId(orderId);
	}
	

}
