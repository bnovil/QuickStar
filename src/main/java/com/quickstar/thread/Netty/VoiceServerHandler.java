package com.quickstar.thread.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/5/3.
 * @Modified By:
 */
public class VoiceServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) throws IOException {

        ByteBuf in = (ByteBuf) msg;
        //读取客户端发送的文件
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        //存放文件
        FileOutputStream fos = new FileOutputStream(new File("E:\\test.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(bytes,0,bytes.length);
        bos.flush();
        in.release();
//        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));        //2
//        ctx.write(in);
        //3
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();                //5
        ctx.close();                            //6
    }
}
