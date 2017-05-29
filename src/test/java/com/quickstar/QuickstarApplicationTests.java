package com.quickstar;

import com.quickstar.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickstarApplicationTests {

	@Resource
	RedisUtil<String , String > redisUtil;
	@Test
	public void redis() {
		redisUtil.set("test", "helloworld");

	}


}
