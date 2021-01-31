package com.egen.orderservicee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.egen.orderservicee.entity.Order;
import com.egen.orderservicee.service.OrderService;
import com.egen.orderservicee.shared.OrderRequest;

@RestController
@RequestMapping("/batchOrder")
@EnableBinding(Source.class)
public class BatchOrderController {
	@Autowired
	private OrderService orderService;
	
	private Logger log = LoggerFactory.getLogger(BatchOrderController.class);
	@Autowired
	private KafkaTemplate<String, OrderRequest> template;

	
	
	private String topic = "batchOrders";
	
	
//	@Autowired
//    private MessageChannel output;
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PostMapping("/createBatchOrder")
	public String createBatchOrder(@RequestBody OrderRequest request) {
		log.info("batch order added to queue successfully");
		template.send(topic, request);
		return "{\"status\": \"Order request created\"}";
	
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PostMapping("/updateBatchOrder/{id}")
	public String updateBatchOrder(@RequestBody Order order, @PathVariable int id) {
		log.info("update order request added to queue successfully");  
		return "Update Order request created";
	
	}

}
