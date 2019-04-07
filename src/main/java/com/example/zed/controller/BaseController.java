package com.example.zed.controller;

import com.example.zed.annotation.Action;
import com.example.zed.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class BaseController {


    @Autowired
    private AsyncService asyncService;

    private static ExecutorService taskExecutor = Executors.newSingleThreadExecutor();

    @RequestMapping("index")
    @Action("index")
    public String index(){
        return "index";
    }

    @RequestMapping("async")
    @Action("async")
    public String async() {
        System.out.println("调用开始");
        Future<String> future = asyncService.asyncPrint();
        Thread listenerThread = new Thread(() -> {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        listenerThread.start();
        System.out.println("调用结束");
        return "sb";
    }
}
