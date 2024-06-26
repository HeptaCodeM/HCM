package com.hcm.grw.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.RoomMessage;


@Service
public class RedisPublisher {
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, RoomMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
