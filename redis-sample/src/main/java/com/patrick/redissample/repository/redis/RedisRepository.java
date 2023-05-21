package com.patrick.redissample.repository.redis;

import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public RedisRepository(
            RedisTemplate<String, String> redisTemplate,
            StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    public String getRedisStringValue(String key) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println(key +" : " + stringValueOperations.get(key));
        return stringValueOperations.get(key);
    }

    public String getRedisValue(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    @PostMapping("")
    public String setRedisKey(Map<String, String> req){
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(req.get("key"), req.get("value"));
        return "set message success";
    }
}
