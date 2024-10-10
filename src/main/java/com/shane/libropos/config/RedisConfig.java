package com.shane.libropos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * RedisConfig
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> template(RedisConnectionFactory connectionFactory) {
    // Create Object
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    // Set connection factory
    template.setConnectionFactory(connectionFactory);
    // Set JSON serialization tools
    GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    // Set serialization of KEYS
    template.setKeySerializer(RedisSerializer.string());
    template.setHashKeySerializer(RedisSerializer.string());
    // Set serialization of VALUES
    template.setValueSerializer(jsonRedisSerializer);
    template.setHashValueSerializer(jsonRedisSerializer);
    return template;
  }
}
