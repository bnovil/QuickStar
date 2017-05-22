package com.quickstar.thread;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lzq
 * @since 2016/9/15 12:49
 * @description
 * @version 1.0
 */
@Configuration
@ComponentScan("com.quickstar.thread.schedule")
@EnableScheduling
public class ScheduleTaskConfig {
}
