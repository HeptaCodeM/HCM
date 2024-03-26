//package com.hcm.grw.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
//@Configuration
//@EnableRedisHttpSession(redisNamespace = "HCM", maxInactiveIntervalInSeconds = 3600)
//public class RedisConfiguration {
//
//	private final RedisPassword REDIS_PASSWORD = RedisPassword.of("1004");
//	
//	@Bean
//	public RedisConnectionFactory connectionFactory() {
//		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//		configuration.setHostName("localhost");
//		configuration.setPort(6379);
//		configuration.setDatabase(0);
//		configuration.setPassword(REDIS_PASSWORD);
//		return new LettuceConnectionFactory(configuration);
//	}
//	
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//		template.setDefaultSerializer(new StringRedisSerializer());
//		template.setConnectionFactory(connectionFactory());
//		return template;
//	}
//	
//}
