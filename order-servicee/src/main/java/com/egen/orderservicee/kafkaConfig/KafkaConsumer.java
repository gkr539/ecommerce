package com.egen.orderservicee.kafkaConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egen.orderservicee.service.OrderService;
import com.egen.orderservicee.serviceImpl.OrderServiceImpl;
import com.egen.orderservicee.shared.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class KafkaConsumer {
	
	private Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	@Autowired
	private OrderService orderService;
	
	@KafkaListener(groupId = "batchOrder-1", topics = "batchOrders", containerFactory = "orderKafkaListnerfactory")
	public void getJsonMsgFromTopic(ConsumerRecords<String, OrderRequest> records) throws JsonProcessingException {
		for (ConsumerRecord<String, OrderRequest> record: records) {
			
			log.info("topic = %s, partition = %d, offset = %d, record = %s\n", record.topic(), record.partition(), record.offset(), record.value().toString());			
			orderService.saveOrder((OrderRequest)record.value());
			log.info("Order is created" + record.value().toString());

		}
		
	}
}

