package com.nd.me.controller;

import com.nd.me.config.annotation.LoginIgnore;
import com.nd.me.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Description 日志检索控制器
 * @Date Create in 下午 4:49 2018/11/22 0022
 */
@RestController
@RequestMapping("/${app.map.ver}/api")
public class SystemLogController extends AbstractController{
    @Autowired
    private LogService logService;

    @LoginIgnore
    @RequestMapping(value = "/logs",method = RequestMethod.GET)
    public Object queryLog(@RequestParam(value = "keyword") String keyword){
        return logService.findAll(keyword);
    }
}
