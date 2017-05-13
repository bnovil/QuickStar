package com.quickstar.common.redis;

import javax.annotation.Resource;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/4/28.
 * @Modified By:
 */
public class RedisTest {
    @Resource
    static RedisUtil<String ,String> redisUtil;
    public static void main(String[] args ) {
        redisUtil.set("12345","test");
        String s = redisUtil.get("12345");
        System.out.println(s);
    }
}
