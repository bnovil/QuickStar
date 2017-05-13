package com.quickstar.thread.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yuton
 * @version 1.0
 * @description 定时任务
 * @since 2016/9/15 15:40
 */
@Slf4j
@Service
public class ScheduleTaskService {

    /**
     * @description 消息推送 每两分钟提示一次
     */
//    @Scheduled(fixedRate = 60 * 1000L * 2)
    public void messagePush() {
    }

    /**
     * @description 低电量监控 每小时提示一次
     */
//    @Scheduled(fixedRate = 60 * 1000 * 60L)
    public void electricityMessage() {
    }

    /**
     * @description 每半小时写入历史位置信息
     */
//    @Scheduled(fixedRate = 60 * 1000 * 30L)
    public void writeHistoryRecord() {
    }

//
//    @Scheduled(cron = "0 00 16 ? * *")
//    public void fixTimeExecution() {
//        System.out.print("每天下午4点执行"+ deteFormat.format(new Date()));
//    }
}
