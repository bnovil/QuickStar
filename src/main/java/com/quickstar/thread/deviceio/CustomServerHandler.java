package com.quickstar.thread.deviceio;

import com.quickstar.common.context.SpringContextUtil;
import com.quickstar.common.redis.RedisOperation;
import com.quickstar.common.redis.RedisUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/4/25 15:50
 */

public class CustomServerHandler extends SimpleChannelInboundHandler<String> {
    private RedisUtil<String,String> redisUtil = SpringContextUtil.getBean(RedisOperation.class);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg);
        String[] data = msg.split("\\|");
        String imei = data[1];
        String storedMsg = null;
        while (true){
            storedMsg = redisUtil.popRightList(imei+"message");
            if (storedMsg != null) {
                channelHandlerContext.writeAndFlush(storedMsg);
            }
            else {
                break;
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
