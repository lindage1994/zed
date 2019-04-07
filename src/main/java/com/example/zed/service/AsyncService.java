package com.example.zed.service;

import java.util.concurrent.Future;

/**
 * @Auther: zed
 * @Date: 2019/3/25 11:19
 * @Description: 异步方法测试类
 */
public interface AsyncService {
    Future<String> asyncPrint();
}
