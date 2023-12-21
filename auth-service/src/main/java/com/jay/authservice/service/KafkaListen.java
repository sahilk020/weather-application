package com.jay.authservice.service;

import com.jay.authservice.entity.UserCredentials;

public interface KafkaListen {
	public  void receiveCredentials(String userCredentials);
}
