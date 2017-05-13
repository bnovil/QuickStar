package com.quickstar.thread.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/5/4.
 * @Modified By:
 */
public class PBServerHandler extends SimpleChannelInboundHandler<Object> {
//    private RedisUtil<String,String> redisUtil = SpringContextUtil.getBean(RedisOperation.class);
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        PersonMsg.Person xxg2 = (PersonMsg.Person) msg;
        ByteBuf in = (ByteBuf) msg;
        byte[] b = new byte[in.readableBytes()];
        in.readBytes(b);

        ByteArrayInputStream input = new ByteArrayInputStream(b);
        // 反序列化
        PersonMsg.Person xxg2 = PersonMsg.Person.parseFrom(input);
        System.out.println("ID:" + xxg2.getId());
        System.out.println("name:" + xxg2.getName());
        System.out.println("email:" + xxg2.getEmail());
        System.out.println("friend:");
        List<String> friends = xxg2.getFriendsList();
        for(String friend : friends) {
            System.out.println(friend);
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
