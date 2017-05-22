package com.quickstar.listener;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author lzq
 * @version 1.0
 * @description Initial context
 * @since 2016/9/3 1:42
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebAppRootContext implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(WebAppRootListener.class);
        servletContext.addListener(NettyServiceListener.class);
        servletContext.setInitParameter(ContextParamDictionary.PROJECT_PATH.getParamName(),
                ContextParamDictionary.PROJECT_PATH.getParamValue());
    }
}
