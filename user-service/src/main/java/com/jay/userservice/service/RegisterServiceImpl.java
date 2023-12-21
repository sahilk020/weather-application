package com.jay.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.userservice.entity.User;
import com.jay.userservice.entity.UserCredentials;
import com.jay.userservice.repository.RegisterRepository;
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;
	@Autowired
	private KafkaPublisherInterface kafkaMessagePublisher;
	@Override
	public User register(User user) {
		UserCredentials userCredentials = new UserCredentials(user.getUsername(),user.getPassword());
		kafkaMessagePublisher.sendEventsToTopic(userCredentials);
		return registerRepository.save(user);
	}

}
