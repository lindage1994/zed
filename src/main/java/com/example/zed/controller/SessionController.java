package com.example.zed.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zed
 * @Date: 2019/4/7 20:37
 * @Description: session controller
 */
@RestController
public class SessionController extends ExceptionController {
    @RequestMapping("getSessionId")
    public Object getSessionId(HttpServletRequest request) {
        return "当前sessionid为：" + request.getSession().getId();
    }
}
