package com.egen.orderservicee.kafkaConfig;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.egen.orderservicee.exception.ResourceNotFound;
import com.egen.orderservicee.service.OrderService;
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
			if(record.value().getPayment() == null){
				log.info("batch order update request");
				try {
					orderService.updateOrder(record.value().getOrder().getId(), record.value().getOrder());
				}
				catch (Exception e) {
					throw new ResourceNotFound("Order with Id " + record.value().getOrder().getId() + " not found.");
				}
				
				
			}
			else {
				orderService.saveOrder((OrderRequest)record.value());
				log.info("Order is created" + record.value().toString());
			}
			

		}
		
	}
}

