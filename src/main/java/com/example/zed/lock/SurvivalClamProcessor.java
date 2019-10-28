package com.example.zed.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zed
 * @Date: 2019/10/28 14:43
 * @Description: redis锁延时守护线程
 */
@Slf4j
public class SurvivalClamProcessor implements Runnable {
    
    private static final int REDIS_EXPIRE_SUCCESS = 1;

    public SurvivalClamProcessor(String key, String value, int lockTime) {
        this.key = key;
        this.value = value;
        this.lockTime = lockTime;
        this.signal = Boolean.TRUE;
    }


    private String key;

    private String value;

    private int lockTime;

    //线程关闭的标记
    private volatile Boolean signal;

    public void stop() {
        this.signal = Boolean.FALSE;
    }

    @Override
    public void run() {
        int waitTime = lockTime * 1000 * 2 / 3;
        while (signal) {
            try {
                Thread.sleep(waitTime);
                if (RedisUtil.expandLockTime(key, value, lockTime)) {
                    if (log.isInfoEnabled()) {
                        log.info("expandLockTime 成功，本次等待{}ms，将重置锁超时时间重置为{}s,,key为{}", waitTime, lockTime, key);
                    }
                } else {
                    if (log.isInfoEnabled()) {
                        log.info("expandLockTime 失败，将导致SurvivalClamConsumer中断");
                    }
                    this.stop();
                }
            } catch (InterruptedException e) {
                if (log.isInfoEnabled()) {
                    log.info("SurvivalClamProcessor 处理线程被强制中断");
                }
            } catch (Exception e) {
                log.error("SurvivalClamProcessor run error", e);
            }
        }
        if (log.isInfoEnabled()) {
            log.info("SurvivalClamProcessor 处理线程已停止");
        }
    }
}
