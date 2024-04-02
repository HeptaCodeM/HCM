package com.hcm.grw.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableRedisHttpSession(redisNamespace = "HCM", maxInactiveIntervalInSeconds = 3600)
public class RedisConfiguration implements BeanClassLoaderAware {

	private ClassLoader loader;
//	private final RedisPassword REDIS_PASSWORD = RedisPassword.of("1004");
	
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.loader = classLoader;
	}
	
	@Bean
	public RedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("158.179.166.134");
		configuration.setPort(6379);
		configuration.setDatabase(0);
//		configuration.setPassword(REDIS_PASSWORD);
		return new LettuceConnectionFactory(configuration);
	}
	
	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
		return new GenericJackson2JsonRedisSerializer(objectMapper);
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory);
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		
		redisTemplate.setValueSerializer(jsonRedisSerializer());
		redisTemplate.setHashValueSerializer(jsonRedisSerializer());
		
		return redisTemplate;
	}
	
	@Bean
	public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		return container;
	}
	
	@Bean
	public Map<String, ChannelTopic> channelTopicMap() {
		Map<String, ChannelTopic> channelTopics = new HashMap<>();
		channelTopics.put("myTopic", new ChannelTopic("myTopic"));
		return channelTopics;
	}
	
	public Jackson2JsonRedisSerializer<Object> jsonRedisSerializer() {
		Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Jackson이 알려지지 않은 속성 무시
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		jsonRedisSerializer.setObjectMapper(objectMapper);
		return jsonRedisSerializer;
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	
}

