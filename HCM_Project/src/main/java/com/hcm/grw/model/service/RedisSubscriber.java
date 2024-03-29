package com.hcm.grw.model.service;

import java.io.IOException;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcm.grw.dto.RoomMessage;
import com.hcm.grw.socket.EchoHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisSubscriber implements MessageListener {
	
	private RedisTemplate<String, Object> redisTemplate; 
	private ObjectMapper objectMapper;
	private EchoHandler echoHandler;
	private String roomMessageValue;
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String body = (String)redisTemplate.getStringSerializer().deserialize(message.getBody());

		try {
			RoomMessage roomMessage = objectMapper.readValue(body, RoomMessage.class);
			roomMessageValue = roomMessage.getMessage();
			log.info("Room - Message : {}", roomMessage.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info(roomMessageValue);
		echoHandler.sendMessageToClients(roomMessageValue);

	}

}
