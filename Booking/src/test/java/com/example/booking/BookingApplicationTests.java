package com.example.booking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BookingApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisScript<Boolean> script;

    @Test
    public void testLock01() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 占位，如果key不存在才可以设置成功
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1");
        // 如果占位成功， 进行正常操作
        if (isLock) {
            valueOperations.set("name", "xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println("name = " + name);
            // 操作结束，删除锁
            redisTemplate.delete("k1");  // 这个方法的弊端，如果本来就有锁了，就直接跳到异常去了，无法删除锁
            // 通过设置超时时间来解决
        } else {
            System.out.println("有线程在使用，请稍后");
        }
    }

    @Test
    public void testLock02() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1", 5, TimeUnit.SECONDS);
        if (isLock) {
            valueOperations.set("name", "xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println("name" + name);
            Integer.parseInt("xxxxxx");
            redisTemplate.delete("k1");
        } else {
            System.out.println("有线程在使用，请稍后");
        }
    }

    @Test
    public void testLock03() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String value = UUID.randomUUID().toString();
        //给锁添加一个过期时间，防止应用在运行过程中抛出异常导致锁无法及时得到释放, value是锁的值，用于判断锁
        Boolean isLock = valueOperations.setIfAbsent("k1", value, 5, TimeUnit.SECONDS);
//没人占位
        if (isLock) {
            valueOperations.set("name", "xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println("name = " + name);
            System.out.println(valueOperations.get("k1"));
//执行lua来释放锁
            Boolean result = (Boolean) redisTemplate.execute(script, Collections.singletonList("k1"), value);
            System.out.println(result);
        } else {
//有人占位，停止/暂缓 操作
            System.out.println("有线程在使用，请稍后");
        }
    }
}


