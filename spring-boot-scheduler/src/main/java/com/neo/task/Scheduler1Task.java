package com.neo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 周期性的执行任务
 */
@Component
public class Scheduler1Task {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * 1.*表示匹配该域的任意值,假如在Minutes域使用*, 即表示每分钟都会触发事件
     * 2.?只能用在dayOfMonth和dayOFWeek两个域,也匹配任意值,但实际不会。因为dayOfMonth和dayOfWeek会相互影响
     *   例如想在每月的20日触发调度，不管20日到底是星期几，则只能使用如下写法：13 * * 20 * ?
     * 3.-表示范围，例如在Minutes域使用5-20，表示从5分到20分钟每分钟触发一次
     * 4./表示起始时间开始触发，然后每隔固定时间触发一次，例如在Minutes域使用5/20,则意味着将在5，25，45分别触发一次
     * 5.,表示列出枚举值值。例如：在Minutes域使用5,20，则意味着在5和20分每分钟触发一次
     * 6.corn表达式一共由七个子表达式组成，前六个必填，最后一个可填，子表达式之间用空格隔开
     */
    @Scheduled(cron = "*/6 * * 20 * ?")
    private void process() {
        System.out.println(Thread.currentThread().getName() + "现在时间：" + dateFormat.format(new Date()));
    }
}
