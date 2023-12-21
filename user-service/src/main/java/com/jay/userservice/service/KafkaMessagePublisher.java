package com.jay.userservice.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.jay.userservice.entity.UserCredentials;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessagePublisher implements KafkaPublisherInterface {

	@Autowired
	private KafkaTemplate<String, Object> template;
	@Override
	public void sendEventsToTopic(UserCredentials userCredentials) {
		try {
			CompletableFuture<SendResult<String,Object>> future = template.send("credentials",userCredentials);
			future.whenComplete((result, exception)->{
				if(exception==null) {
					log.info("sent message=[{}] with offset=[{}]",userCredentials.toString(),result.getRecordMetadata().offset());
				}
			});
		}catch(Exception ex) {
			log.info("Error {}",ex.getMessage());
		}
	}
}
