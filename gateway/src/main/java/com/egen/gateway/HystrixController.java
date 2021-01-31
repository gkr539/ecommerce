package com.egen.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HystrixController {
	
	@RequestMapping("/orderFailure")
	public Mono<String> orderServiceFailure(){
		return Mono.just("Order Service is not responding. Try again later.");
	}
	
	@RequestMapping("/paymentFailure")
	public Mono<String> paymentServiceFailure(){
		return Mono.just("Order Service is not responding. Try again later.");
	}
	
	@RequestMapping("/batchOrderFailure")
	public Mono<String> batchOrderServiceFailure(){
		return Mono.just("Batch Order Service is not responding. Try again later.");
	}

}