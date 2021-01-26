package com.neo.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时的执行任务
 */
@Component
public class Scheduler2Task {
    private static final SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private static final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 上任务开始时开始计算时间
     */
    @Async
    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        System.out.println(Thread.currentThread().getName() + "现在时间：" + simpleDateFormat1.format(new Date()));
    }

    /**
     * 上任务结束后开始计算时间
     */
    @Async
    @Scheduled(fixedDelay = 1000)
    public void delayCurrentTime() {
        System.out.println(Thread.currentThread().getName() + "现在时间：" + simpleDateFormat2.format(new Date()));
    }
}
