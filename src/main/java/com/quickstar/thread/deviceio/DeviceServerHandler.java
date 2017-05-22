package com.quickstar.thread.deviceio;

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
public class DeviceServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        ByteBuf in = (ByteBuf) msg;
        ByteBuf tofile = (ByteBuf) msg;
        String str = in.toString(CharsetUtil.UTF_8);
        log.info(str);
        String[] data = str.split("\\*");
        String subdata = data[3].substring(0,2);
        if ("LK".equals(subdata)) {
            String toclient = data[0]+"*"+data[1]+"*"+"0002"+"*"+"LK]";
            log.info("send to client: "+toclient);
            ByteBuf b = Unpooled.copiedBuffer(toclient,CharsetUtil.UTF_8);
            ctx.writeAndFlush(b);

        }
        if ("UP".equals(subdata)) {
            log.info("change time internal succeed");
        }
        if ("TK".equals(subdata)) {
            FileOutputStream fos = new FileOutputStream(new File("C:\\watchtest\\w"+data[2]+".amr"));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] tf = new byte[tofile.readableBytes()];
            tofile.readBytes(tf);
            int start = data[0].length()+1+data[1].length()+1+data[2].length()+4;
            bos.write(tf, start, tf.length - start -1);
            bos.flush();
        }
        ByteBuf timeinternal = Unpooled.copiedBuffer((data[0]+"*"+data[1]+"*"+data[2]+"*"+"UPLOAD,1]"),CharsetUtil.UTF_8);
        ctx.writeAndFlush(timeinternal);
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
