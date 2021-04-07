package com.neo;

import com.neo.domain.User;
import com.neo.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisUtil redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        System.out.println(redisUtils.get("aaa"));
    }

    @Test
    public void testString() {
        redisUtils.set("string", "AAA", 100l, TimeUnit.SECONDS);
        redisUtils.set("Object", new User("aa@126.com", "aa", "aa123456", "aa", "123"));
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
            list.add(user);
        }
        redisUtils.set("list", list);
        System.out.println(redisUtils.get("string"));
        System.out.println(redisUtils.get("Object"));
    }

    @Test
    public void testHash() {
        redisUtils.hSet("hash", "AAA", "aaa");
        redisUtils.hSet("hash", "BBB", "bbb");
        System.out.println(redisUtils.hGet("hash", "AAA"));
    }

    @Test
    public void testList() {
        User user1 = new User("aa@126.com", "aa", "aa123456", "aa", "123");
        User user2 = new User("aa@126.com", "aa", "aa123456", "aa", "123");
        User user3 = new User("aa@126.com", "bb", "aa123456", "bb", "123");
        redisUtils.lPush("list", user1);
        redisUtils.lPush("list", user2);
        redisUtils.lPush("list", user3);
        System.out.println(redisUtils.lRange("list", 0l, 2l));
    }

    @Test
    public void testSet() {
        User user1 = new User("aa@126.com", "caa", "aa123456", "caa", "123");
        User user2 = new User("aa@126.com", "caa", "aa123456", "caa", "123");
        User user3 = new User("aa@126.com", "abb", "aa123456", "abb", "123");
        redisUtils.add("set", user1);
        redisUtils.add("set", user2);
        redisUtils.add("set", user3);
        System.out.println(redisUtils.setMembers("set"));
    }

    @Test
    public void testZSet() {
        User user1 = new User("aa@126.com", "caa", "aa123456", "caa", "123");
        User user2 = new User("aa@126.com", "caa", "aa123456", "caa", "123");
        User user3 = new User("aa@126.com", "abb", "aa123456", "abb", "123");
        redisUtils.zAdd("zSet", user1, 1.5);
        redisUtils.zAdd("zSet", user2, 2);
        redisUtils.zAdd("zSet", user3, 0.9);
        System.out.println(redisUtils.rangeByScore("zSet", 1, 3));
    }

    @Test
    public void remove() {
        redisUtils.remove("aaa");
        redisUtils.remove("Object", "string");
    }

    @Test
    public void redis() {
        Cache cache = redisCacheManager.getCache("getUser");
        System.out.println(cache.get("UserRedisController.getUser").get());
    }
}