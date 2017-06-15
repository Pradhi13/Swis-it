package com.stackroute.swisit.messageservice;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageService {
	public void publishmessage(String topic,String message) throws JsonProcessingException;
	public void listenmessage(String topic);
}
