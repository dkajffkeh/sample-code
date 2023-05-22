package com.patrick.redissample.api;

import com.patrick.redissample.repository.redis.RedisRepository;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/list/{rightValue}")
    public String rightPush(@PathVariable("rightValue") int rightValue) {
        redisRepository.rightPush(rightValue);
        return "success";
    }

    @GetMapping("list")
    public List<String> getList() {
        return redisRepository.getList();
    }

    @PostMapping("/set/{setValue}")
    public String setValue(@PathVariable("setValue") int setValue) {
        redisRepository.setPush(setValue);
        return "Success";
    }

    @GetMapping("/set")
    public Set<String> getSet() {
        return redisRepository.getIntegers();
    }

    @PostMapping("/hash/{key}/{value}")
    public void setHash(@PathVariable String key, @PathVariable String value) {
        redisRepository.setHash(key,value);
    }

    @GetMapping("/hash/{key}")
    public String getHashVal(@PathVariable String key) {
        return redisRepository.getHash(key);
    }

}
