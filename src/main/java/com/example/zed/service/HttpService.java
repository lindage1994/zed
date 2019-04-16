package com.example.zed.service;

import java.util.Map;

/**
 * @Auther: zed
 * @Date: 2019/4/16 09:30
 * @Description: http请求
 */
public interface HttpService {
    Object getResult(Map params);
    Object getPostResult(String ip);
}
