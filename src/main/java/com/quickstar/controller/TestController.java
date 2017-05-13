package com.quickstar.controller;

import com.quickstar.common.message.CopyWriteUI;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lzq
 * @version 1.0
 * @description
 * @since 2017/4/5 11:08
 */
@Slf4j
@RestController
public class TestController {
    @Resource
    private CopyWriteUI copyWriteUI;
    @Resource
    private RedisProperties redisProperties;

    /**
     * 接口说明
     * 请求示例
     * @return String
     * @throws JsonProcessingException
     */
    @RequestMapping("/getUserInfo")
    public String getUserInfo() throws JsonProcessingException {
        System.out.println(redisProperties.getHost());
        System.out.println(redisProperties.getPort());
        return copyWriteUI.getImageIp();
    }
}
