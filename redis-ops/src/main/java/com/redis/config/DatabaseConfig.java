package com.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
public class DatabaseConfig {

    @Bean
    public JedisPooled jedisClient() {
        return new JedisPooled("192.168.0.108", 6399);
    }
}
