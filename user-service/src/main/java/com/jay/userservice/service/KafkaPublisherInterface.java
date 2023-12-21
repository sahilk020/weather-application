package com.jay.userservice.service;

import com.jay.userservice.entity.UserCredentials;

public interface KafkaPublisherInterface {
	public void sendEventsToTopic(UserCredentials userCredentials);
}
