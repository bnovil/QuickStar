package com.quickstar.thread.deviceio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/4/25 16:48
 */
public class NettyClient {
    private int port;
    private String host;

    public NettyClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class)

                .option(ChannelOption.SO_KEEPALIVE, true)
                .group(eventLoopGroup)
                .remoteAddress(host, port)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel)
                            throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                SocketChannel socketChannel = (SocketChannel) future.channel();
                System.out.println("----------------connect server success----------------");
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient(1024, "127.0.0.1");
        nettyClient.start();
    }
}
