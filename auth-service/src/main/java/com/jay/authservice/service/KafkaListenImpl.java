package com.jay.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.authservice.entity.UserCredentials;
import com.jay.authservice.repository.AuthRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaListenImpl implements KafkaListen {
	@Autowired
	ObjectMapper objectMapper;
	UserCredentials credentials;
	@Autowired
	AuthRepository authRepository;

	@KafkaListener(topics = { "credentials" }, groupId = "group-1")
	@Override
	public void receiveCredentials(String userCredentials) {

		try {
			credentials = objectMapper.readValue(userCredentials, UserCredentials.class);
			log.info("received userCredentials {}", credentials);
			authRepository.save(credentials);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
