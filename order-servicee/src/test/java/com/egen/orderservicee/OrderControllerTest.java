package com.egen.orderservicee;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void getOrderDetails() {
		
		
		
		
		
		RestAssured.baseURI = "http://localhost:9000/order/getOrderById";
		
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//response object
		Response response = httpRequest.request(Method.GET, "2");
		
		String responseBody = response.getBody().asString();
		System.out.println("response body " + responseBody);
		
		//status code validation
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		
	}
	
	
}
