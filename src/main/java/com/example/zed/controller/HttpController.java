package com.example.zed.controller;

import com.example.zed.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zed
 * @Date: 2019/4/16 09:56
 * @Description: http controller
 */
@RestController
public class HttpController extends ExceptionController{

    @Autowired
    HttpService httpService;

    @RequestMapping("getWeather")
    public Object getWeather(String cityName) {

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("cityName",cityName);

        return httpService.getResult(paramMap);
    }

    @RequestMapping("getIp")
    public Object getIp(String ip) {
        return httpService.getPostResult(ip);
    }
}
