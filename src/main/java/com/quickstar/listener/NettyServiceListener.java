package com.quickstar.listener;

import com.quickstar.thread.deviceio.NettyServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/4/28 10:15
 */
public class NettyServiceListener implements ServletContextListener {
    private NettyServer nettyServer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (null == nettyServer) {
            nettyServer = new NettyServer(1014);
            nettyServer.start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        nettyServer.closeNettyService();
    }
}
