package com.zhang.zhangFriend.service;

import com.zhang.zhangFriend.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * Redis 测试
 *
 
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("zhangString", "dog");
        valueOperations.set("zhangInt", 1);
        valueOperations.set("zhangDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("zhang");
        valueOperations.set("zhangUser", user);
        // 查
        Object zhang = valueOperations.get("zhangString");
        Assertions.assertTrue("dog".equals((String) zhang));
        zhang = valueOperations.get("zhangInt");
        Assertions.assertTrue(1 == (Integer) zhang);
        zhang = valueOperations.get("zhangDouble");
        Assertions.assertTrue(2.0 == (Double) zhang);
        System.out.println(valueOperations.get("zhangUser"));
        valueOperations.set("zhangString", "dog");
        redisTemplate.delete("zhangString");
    }
}
