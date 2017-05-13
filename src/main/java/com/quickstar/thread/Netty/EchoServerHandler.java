package com.quickstar.thread.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        ByteBuf in = (ByteBuf) msg;
        ByteBuf tofile = (ByteBuf) msg;
        String str = in.toString(CharsetUtil.UTF_8);
        log.info(str);
        String[] data = str.split("\\*");
        String subdata = data[3].substring(0,2);
        if ("LK".equals(subdata)) {
            String toclient = data[0]+"*"+data[1]+"*"+data[2]+"*"+"LK]";
            in.clear();
            in.writeBytes(toclient.getBytes());
            ctx.channel().writeAndFlush(in);
            System.out.println(toclient);
        }
        if ("TK".equals(subdata)) {
            FileOutputStream fos = new FileOutputStream(new File("C:\\watchtest\\w"+data[2]+".txt"));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] tf = new byte[tofile.readableBytes()];
            tofile.readBytes(tf);
            int start = data[0].length()+1+data[1].length()+1+data[2].length()+4;
            bos.write(tf, start, tf.length - start - 1);
            bos.flush();
        }
    }


//        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));        //2
//        ctx.write(in);          //3

//        ChannelPipeline cp = ctx.pipeline();
//        System.out.println("ChannelPipeline "+cp.hashCode());
//        Channel c = ctx.channel();
//        ChannelPipeline cp2 = c.pipeline();
//        System.out.println("ChannelPipeline2 "+cp2.hashCode());


        @Override
        public void channelReadComplete (ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                    .addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught (ChannelHandlerContext ctx,
                Throwable cause){
            cause.printStackTrace();                //5
            ctx.close();                            //6
        }
    }
