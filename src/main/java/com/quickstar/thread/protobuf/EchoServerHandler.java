package com.quickstar.thread.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/4/27.
 * @Modified By:
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private final Logger log =  LoggerFactory.getLogger(this.getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        ByteBuf in = (ByteBuf) msg;
        String message = in.toString(CharsetUtil.UTF_8);
        log.info("received message : {}", message);
    }


        @Override
        public void channelReadComplete (ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                    .addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught (ChannelHandlerContext ctx,
                Throwable cause){
            cause.printStackTrace();
            ctx.close();
        }
    }
