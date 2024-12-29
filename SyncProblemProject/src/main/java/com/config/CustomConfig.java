package com.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

import javax.sql.DataSource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CustomConfig {

	@Bean
	CacheManager redisConnection(RedisConnectionFactory redis) {
		// To check whether redis is connected successfully or 
		testRedisConnection(redis);
		
		// Setting Default configuration for redis
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(10))
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) // make key in human readable format
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // make value in human readable format
				
		return RedisCacheManager.builder(redis).cacheDefaults(config).build(); 
	}
	private void testRedisConnection (RedisConnectionFactory redis) {
		
		int currentAttempt = 0;
		int max_attempt = 5;
		long maxDelay = 2000; // 2sec
		
		while(currentAttempt < max_attempt) {
			try {
				currentAttempt++; 
				RedisConnection connection = redis.getConnection();
				if (connection.ping() != null) {
	                System.out.println("Redis is connected successfully");
	                return;
	            } else {
	                System.err.println("Redis connection failed.");
	            }
	        } 
			catch (Exception e) {
	            System.err.println("Error checking Redis connection: " + e.getMessage());
	        }
			//Delay before next try
			try {
                Thread.sleep(maxDelay);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Retry interrupted", e);
            }
			
		}
		// After max_attempts
		throw new RuntimeException("Unable to connect to Redis");
		
		
	}
	
	@Bean
	boolean checkDatabaseConnection(DataSource ds) {
		try {
			Connection connection = ds.getConnection();
            if (connection.isValid(2)) {
                System.out.println("MySQL Database is connected successfully");
                return true;
            } else {
                throw new RuntimeException("SQL Database connection failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error checking SQL Database connection: " + e.getMessage());
            throw new RuntimeException("Unable to connect to SQL Database", e);
        }
	}
}
