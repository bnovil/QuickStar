package com.quickstar.thread.Netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/5/6.
 * @Modified By:
 */
@Slf4j
public class StringServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg);
        String[] data = msg.split("\\*");
        String subdata = data[3].substring(0,2);
        if ("LK".equals(subdata)) {
            String toclient = data[0]+"*"+data[1]+"*"+data[2]+"*"+"LK]";
//            in.clear();
//            in.writeBytes(toclient.getBytes(CharsetUtil.UTF_8));
//            ctx.channel().writeAndFlush(in);
            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(toclient,CharsetUtil.UTF_8));
            channelHandlerContext.writeAndFlush(toclient);
            System.out.println(toclient);
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
