package com.patrick.redissample.repository.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getRedisValue(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    public String setStringKey(Map<String, String> req){
        ValueOperations<String, Object> vop = redisTemplate.opsForValue();
        vop.set(req.get("key"), req.get("value"));
        return "set message success";
    }

    public void rightPush(int rightValue) {
        redisTemplate.opsForList().rightPush("list",String.valueOf(rightValue));
    }

    public List<String> getList() {
        List<Object> values = redisTemplate.opsForList().range("list", 0, -1);
        List<String> integerValues = new ArrayList<>();
        for (Object value : values) {
            integerValues.add((String) value);
        }
        return integerValues;
    }

    public void setPush(int setValue) {
        redisTemplate.opsForSet().add("set",String.valueOf(setValue));
    }

    public Set<String> getIntegers() {
        Set<Object> values = redisTemplate.opsForSet().members("set");
        Set<String> integers = new HashSet<>();
        for (Object value : values) {
            integers.add((String) value);
        }
        return integers;
    }

    public void setHash(String key, String value) {
        redisTemplate.opsForHash().put("hash",key,value);
    }

    public String getHash(String key) {
        return (String)redisTemplate.opsForHash().get("hash",key);
    }
}
