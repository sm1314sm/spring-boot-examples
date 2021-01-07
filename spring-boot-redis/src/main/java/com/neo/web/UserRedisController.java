package com.neo.web;

import com.neo.domain.User;
import com.neo.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 指定默认缓存区
 * 缓存区：key的前缀，与指定的key构成redis的key，如 user::10001
 */
@CacheConfig(cacheNames = {"one", "two"})
@RestController
public class UserRedisController {
    public static final Logger log = LoggerFactory.getLogger(UserRedisController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtils;

    /**
     * 一般在查询中使用，缓存有数据时，从缓存获取。没有数据时，执行方法，并将返回值保存到缓存中
     * 1. cacheNames指定缓存区，与value效果相同，没有配置时使用@CacheConfig中指定的缓存区
     * 2. key指定缓存区的key，可用SpEL表达式，也可以用keyGenerator自定义key的生成方式
     * 3. condition指定满足什么条件时才缓存，可用SpEL表达式(如#id,#p0,#a0,#result等)，unless效果相反
     */
    @RequestMapping("/getUser/{id}")
    @Cacheable(cacheNames = "getUser", keyGenerator = "keyGenerator", condition = "#a0 gt 10")
    public User getUser(@PathVariable("id") Long id) {
        User user = new User("aa@1262.com", "aa", "aa123456", "aa", "123");
        user.setId(id);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功" + user.toString());
        return user;
    }

    /**
     * 一般在新增和修改中使用，一定会执行方法，并将返回值保存到缓存中
     * 当key的值不存在时新增，存在时修改
     */
    @RequestMapping("/putUser/{str}")
    @CachePut(value = "userCache", key = "#password", unless = "#p1 eq '56'")
    public User putUser(final User user, @PathVariable("str") String password) {
        UUID uuid = UUID.randomUUID();
        user.setPassword(password + uuid);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    /**
     * 一般在删除中使用，用beforeInvocation指定是否要执行方法
     * allEntries = true ：默认false，删除整个缓存区的所有值，此时指定的key无效
     * beforeInvocation = true ：默认false，表示调用方法之后删除缓存数据；true时，在调用之前删除缓存数据
     */
    @RequestMapping("/evictUser/{str}")
    @CacheEvict(value = "userCache", key = "#password", condition = "#password eq '50'", beforeInvocation = true)
    public void evictUser(@PathVariable("str") String password) {
        System.out.println(password);
    }

    /**
     * 根据RedisTemplate查询缓存是否存在并返回结果
     */
    @RequestMapping(value = "/hello/{id}")
    public User hello(@PathVariable(value = "id") String id) {
        //查询缓存中是否存在
        User user;
        if (redisUtils.exists(id)) {
            //获取缓存
            user = (User) redisUtils.get(id);
            log.info("从缓存获取的数据" + user);
        } else {
            user = new User("youyu@613.com", "不败的求道者", "aa123456", "葛贺祥", "12321");
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id, user, 10L, TimeUnit.MINUTES);
            log.info("数据插入缓存" + user);
        }
        return user;
    }

    /**
     * 根据stringRedisTemplate查询缓存是否存在
     */
    @RequestMapping(value = "/test/{string}")
    public Boolean test(@PathVariable("string") String testString) {
        //查询缓存中是否存在
        boolean hasKey = stringRedisTemplate.hasKey(testString);
        if (hasKey) {
            //获取缓存
            Object object = stringRedisTemplate.opsForValue().get(testString);
            System.out.println(object);
        }
        return hasKey;
    }

    /**
     * 根据RedisTemplate查询缓存是否存在
     */
    @RequestMapping(value = "/testNoString/{string}")
    public Boolean testNoString(@PathVariable("string") String testString) {
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists(testString);
        if (hasKey) {
            //获取缓存
            Object object = redisUtils.get(testString);
            System.out.println(object);
        }
        return hasKey;
    }
}