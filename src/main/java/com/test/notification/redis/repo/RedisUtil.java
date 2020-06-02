package com.test.notification.redis.repo;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class RedisUtil<T> {

	private RedisTemplate<String, T> redisTemplate;

	private HashOperations<String,String, T> hashOperation;
	private ListOperations<String,T> pushOperation;
	

	@Autowired
	RedisUtil(RedisTemplate<String,T> redisTemplate) {
		this.redisTemplate = redisTemplate;

		this.hashOperation = redisTemplate.opsForHash();
		this.pushOperation = redisTemplate.opsForList();
	

	}

	public RedisTemplate<String,T> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String,T> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	

	
	public T readQueue(String redisMapKey,long timeOut) {
		return this.pushOperation.leftPop(redisMapKey,timeOut, TimeUnit.SECONDS);
	}

	public Long queueLength(String redisMapKey) {
		return this.pushOperation.size(redisMapKey);
	}
	

}