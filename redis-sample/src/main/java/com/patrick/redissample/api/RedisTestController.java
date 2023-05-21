package com.patrick.redissample.api;

import com.patrick.redissample.repository.redis.RedisRepository;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class RedisTestController {

    private final RedisRepository redisRepository;

    public RedisTestController(
            RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @GetMapping("/{key}")
    public String redisTest(@PathVariable String key) {
        return redisRepository.getRedisValue(key);
    }

    @PostMapping
    public void setValue(@RequestBody Map<String, String> req) {
        System.out.println(redisRepository.setRedisKey(req));

    }
}
