package com.example.zed.service.impl;

import com.example.zed.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Auther: zed
 * @Date: 2019/3/25 11:21
 * @Description: 异步方法实现
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public Future<String> asyncPrint() {
        log.info("异步方法执行........");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("hello motor");
    }
}
