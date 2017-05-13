package com.quickstar;

import com.quickstar.common.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickstarApplicationTests {

	@Resource
	RedisUtil redisUtil;
	@Test
	public void contextLoads() {
	}


}
