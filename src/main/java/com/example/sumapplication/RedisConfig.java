package com.example.sumapplication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // Init Configuration RedisTemplate
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // Se sustituye la serialización utilizando la librería Jackson
        /*Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);*/
        //Establecimiento de la estructura para Serializar
        //Key Serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        /*        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);*/
        //Hashes Serializer
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        /*        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);*/
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
