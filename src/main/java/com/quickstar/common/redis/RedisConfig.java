package com.quickstar.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @author lzq
 * @version 1.0
 * @description
 * @since 2017/1/16 9:34
 */
@Configuration
public class RedisConfig {


    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(100);
        config.setMaxWaitMillis(5000);
        config.setMinIdle(0);
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        config.setNumTestsPerEvictionRun(2);
        config.setTimeBetweenEvictionRunsMillis(30000);
        config.setMinEvictableIdleTimeMillis(60000);
        config.setSoftMinEvictableIdleTimeMillis(60000);
        return config;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        redisConnectionFactory.setDatabase(2);
        redisConnectionFactory.setHostName("127.0.0.1");
        redisConnectionFactory.setPort(6379);
        redisConnectionFactory.setTimeout(15000);
        redisConnectionFactory.setUsePool(true);
        return redisConnectionFactory;
    }

    @Bean
    public <K, V> RedisTemplate<K, V> redisTemplate() {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
