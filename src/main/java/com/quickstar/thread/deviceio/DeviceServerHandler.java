package com.quickstar.thread.deviceio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/4/27.
 * @Modified By:
 */
public class DeviceServerHandler extends ChannelInboundHandlerAdapter {

    private final Logger log =  LoggerFactory.getLogger(this.getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        ByteBuf in = (ByteBuf) msg;
        String str = in.toString(CharsetUtil.UTF_8);
        log.info(str);
        ctx.writeAndFlush("server received ");
    }

    //连接建立
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }

    //连接断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelInactive();
    }




    @Override
        public void channelReadComplete (ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                    .addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught (ChannelHandlerContext ctx,
                Throwable cause){
            cause.printStackTrace();                //5
            // 抛出错误时处理方法
        }
    }
