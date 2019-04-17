package com.example.zed.controller;

import com.example.zed.annotation.Action;
import com.example.zed.error.BusinessErrorEnum;
import com.example.zed.error.BusinessException;
import com.example.zed.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@Slf4j(topic = "this is baseController")
public class BaseController extends ExceptionController{


    @Autowired
    private AsyncService asyncService;

    private static ExecutorService taskExecutor = Executors.newSingleThreadExecutor();

    @RequestMapping("index")
    @Action("index")
    public String index(){
        return "index";
    }

    @RequestMapping("exception")
    public Object exception() throws Exception{
        throw new Exception();
    }

    @RequestMapping("businessException")
    public Object businessException() throws Exception {
        throw new BusinessException(BusinessErrorEnum.USER_STATUS_ERROR);
    }

    @RequestMapping("async")
    @Action("async")
    public String async() {
        log.info("日志开始打印........");
        System.out.println("调用开始");
        Future<String> future = asyncService.asyncPrint();
        Thread listenerThread = new Thread(() -> {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        listenerThread.setDaemon(true); //守护线程
        listenerThread.start();
        System.out.println("调用结束");
        return "sb";
    }
}
