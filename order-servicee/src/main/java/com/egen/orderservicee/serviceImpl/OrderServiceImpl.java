package com.egen.orderservicee.serviceImpl;




import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.egen.orderservicee.entity.Order;
import com.egen.orderservicee.entity.OrderStatus;
import com.egen.orderservicee.entity.ShippingDetails;
import com.egen.orderservicee.entity.ShippingStatus;
import com.egen.orderservicee.exception.ResourceNotFound;
import com.egen.orderservicee.repository.OrderRepository;
import com.egen.orderservicee.service.OrderService;
import com.egen.orderservicee.shared.OrderRequest;
import com.egen.orderservicee.shared.OrderResponse;
import com.egen.orderservicee.shared.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
@RefreshScope
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	
	
//	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
//	private String PAYMENT_URL;

	@Override
	public OrderResponse saveOrder(OrderRequest request) throws JsonProcessingException {
		//get order ID by hashing user email and time stamp
		Order order = request.getOrder();
		LocalDateTime date  = java.time.LocalDateTime.now();
		String email = order.getName();
		String hashKey = getHashKey(date.toString() + email).substring(0, 9);
		order.setOrder_id(hashKey);
		ShippingDetails sd = order.getShippingDetails();
		sd.setShippingStatus(ShippingStatus.CREATED.getStatus());
		order.setShippingDetails(sd);
		
		Payment payment = request.getPayment();
		payment.setOrderId(order.getOrder_id());
		payment.setAmount(order.getPrice());
		log.info("OrderServiceImpl request: {}", new ObjectMapper().writeValueAsString(request));
		log.info("Calling Payment Service. ");
		Payment paymentResponse = restTemplate.postForObject("http://payment-service/payment/createPayment", payment,  Payment.class);
		
		String response = "";
		if(paymentResponse.getStatus().contentEquals("Success")) {
			response = "Payment successful and order placed";
			order.setOrderStatus(OrderStatus.SUCCESS.getStatus());
		}
		else {
			response = "payment failed";
			order.setOrderStatus(OrderStatus.FAILED.getStatus());
		}
		orderRepository.save(order);
		return new OrderResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(), response );
		
		
	}
	
	public String getHashKey(String key) {		
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(key.getBytes());
		    byte[] digest = md.digest();
		    String myHash = DatatypeConverter
		      .printHexBinary(digest).toLowerCase();
		    return myHash;	            
        }  

        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
	}

	@Override
	public String cancelOrder(Long id) {
		Order order = getOrderById(id);
		if(order.getOrderStatus() == (OrderStatus.SUCCESS.getStatus())) {
			order.setOrderStatus(OrderStatus.CANCELLED.getStatus());
			orderRepository.save(order);
			return "Order Cancelled";
		}
		else {
			return "Order Not Found";
		}		
	}
	
	
	
	
	@Override
	public Order getOrderById(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Order with id " + id + " not found.") );
		return order;
	}

	@Override
	public Order updateOrder(Long id, Order order) {
		Order stored_order = getOrderById(id);
		log.info("update order " + order.toString());
		stored_order.setBillingAddress(order.getBillingAddress())
					.setPrice(order.getPrice())
					.setShippingAddress(order.getShippingAddress())
					.setOrderStatus(order.getOrderStatus())
					.setOrderItems(order.getOrderItems())
					.setName(order.getName());
					
		
		orderRepository.save(stored_order);
		return stored_order;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	

}
