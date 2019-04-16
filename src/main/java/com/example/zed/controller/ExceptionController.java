package com.example.zed.controller;

import com.example.zed.error.BusinessErrorEnum;
import com.example.zed.error.BusinessException;
import com.example.zed.response.CommonResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @Auther: zed
 * @Date: 2019/4/16 15:34
 * @Description: 统一异常处理类
 */
public class ExceptionController {

    /**
     *
     * controller统一异常处理
     * @param ex 抛出的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(Exception ex) {
        CommonResponseObject responseObject;
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseObject = CommonResponseObject.error(businessException.getErrCode(),businessException.getErrMsg());
        } else {
            responseObject = CommonResponseObject.error(BusinessErrorEnum.UNKNOW_ERROR.getErrCode(), BusinessErrorEnum.UNKNOW_ERROR.getErrMsg());
        }
        return responseObject;
    }
}
