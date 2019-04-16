package com.example.zed.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: zed
 * @Date: 2019/4/16 15:40
 * @Description: 通用的返回对象
 */
public class CommonResponseObject {
    @Setter
    @Getter
    private Integer code;
    @Setter
    @Getter
    private String message;
    @Setter
    @Getter
    private Object data;

    public static CommonResponseObject success() {
        CommonResponseObject object = new CommonResponseObject();
        object.setCode(0);
        object.setMessage("success");
        return object;
    }

    public static CommonResponseObject success(Object data) {
        CommonResponseObject object = new CommonResponseObject();
        object.setCode(0);
        object.setMessage("success");
        object.setData(data);
        return object;
    }

    public static CommonResponseObject error(Integer code, String message) {
        CommonResponseObject object = new CommonResponseObject();
        object.setCode(code);
        object.setMessage(message);
        return object;
    }
}
