package com.example.zed.error;

/**
 * @Auther: zed
 * @Date: 2019/4/16 15:51
 * @Description: 通用错误接口
 */
public interface CommonError {
    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
