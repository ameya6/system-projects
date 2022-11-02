package com.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.json.Path;

import java.time.Duration;

@Repository
public class ShortURLRedisDao<T> {

    @Autowired
    JedisPooled jedisClient;

    public void set(String key, T data) {
        jedisClient.jsonSet(key, data);
    }

    public void set(String key, String path, T data) {
        jedisClient.jsonSet(key, new Path(path), data);
    }
}
