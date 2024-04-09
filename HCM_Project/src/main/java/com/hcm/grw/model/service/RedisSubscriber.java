package com.hcm.grw.model.service;


import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;



@Service
public class RedisSubscriber implements MessageListener {
	
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate; 
//	@Autowired
//	private ObjectMapper objectMapper;
//	private EchoHandler echoHandler;
//	private String roomMessageValue;
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
//		String body = (String)redisTemplate.getStringSerializer().deserialize(message.getBody());
//
//		try {
//			RoomMessage roomMessage = objectMapper.readValue(body, RoomMessage.class);
//			roomMessageValue = roomMessage.getMessage();
//			log.info("Room - Message : {}", roomMessage.toString());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		log.info(roomMessageValue);
//		echoHandler.sendMessageToClients(roomMessageValue);

	}

}
