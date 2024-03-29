package com.egen.orderservicee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.egen.orderservicee.entity.Order;
import com.egen.orderservicee.service.OrderService;
import com.egen.orderservicee.shared.OrderRequest;
import com.egen.orderservicee.shared.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	private Logger log = LoggerFactory.getLogger(OrderController.class);
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Order Created")
	})
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/createOrder")
	public OrderResponse createOrder(@RequestBody OrderRequest request) throws JsonProcessingException {
		log.info("In order-service order controller create request " + request.getOrder().getId());	
		return orderService.saveOrder(request);
	}
	
	@PutMapping("/cancelOrder/{id}")
	public ResponseEntity<?> cancelOrder(@PathVariable Long id){
		try {
			orderService.cancelOrder(id);
			log.info("Order cancelled with id "+ id);
			return new ResponseEntity<>("Order with id " + id + " is cancelled.", HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("Error cancelling order with id "+ id);
			return new ResponseEntity<>("No Order found with id " + id, HttpStatus.NOT_FOUND);
		}			
	}
	
	@PostMapping("/updateOrder/{id}")
	public Order updateOrder(@RequestBody Order order, @PathVariable Long id) {
		return orderService.updateOrder(id, order);	
	}
	
	@GetMapping("/getOrderById/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		log.info("In order-service order controller get order request ");
		Order order = orderService.getOrderById(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	

	

}
