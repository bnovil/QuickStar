package com.quickstar.controller;


import com.quickstar.common.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/4/27.
 * @Modified By:
 */
@Slf4j
@RestController
public class ToClientController {

    @Resource
    RedisUtil<String,String> redisUtil ;

    @RequestMapping("/toclient")
    public String toclient(String imei,String command) {
        //将APP发送给硬件的消息存在redis中
        redisUtil.pushList(imei+"message",command);
        log.info("write app command to redis,imei:{},command: {}",imei,command);
        String resp = "Send message: "+ command +" to device will be done in 2 minutes";
        return resp;
    }



}
