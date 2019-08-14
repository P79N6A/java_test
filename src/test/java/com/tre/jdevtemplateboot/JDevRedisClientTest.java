package com.tre.jdevtemplateboot;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

/**
 * @description:
 * @author: JDev
 * @create: 2018-12-26 15:04
 **/
//根据测试方法名字搞定执行顺序
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDevRedisClientTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final static String KEY = "username";
    private final static String VALUE = "test";

    @Test
    public void AstringRedis(){
        redisTemplate.opsForValue().set(KEY,VALUE);
        logger.info("string set存储:{}->{}",KEY,VALUE);
        redisTemplate.opsForValue().get(KEY);
        logger.info("string 根据{}取出{}",KEY,VALUE);
    }

}
