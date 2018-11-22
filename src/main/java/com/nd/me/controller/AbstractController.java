package com.nd.me.controller;

import com.alibaba.fastjson.JSONObject;
import com.nd.me.BizException;
import com.nd.me.exception.ErrorMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author
 * @Description
 * @Date Create in 上午 10:23 2018/11/19 0019
 */
public abstract class AbstractController {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Object exceptionHandler(Exception exception){

        if(exception instanceof BizException){
            BizException bizException = (BizException)exception;
            return new ErrorMessage(bizException.getStatus(),bizException.getCode(),bizException.getMessage());
        }

        return new JSONObject();
    }
}
