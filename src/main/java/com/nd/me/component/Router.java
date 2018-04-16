package com.nd.me.component;

import com.nd.me.service.UserInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Router {
    private static Map<Integer,UserInfoService> userInfoServiceMap;

    @Resource
    public void setUserInfoServiceMap(List<UserInfoService> services){
        userInfoServiceMap = services.stream().collect(Collectors.toMap(UserInfoService::getType,userInfoService -> userInfoService));
    }

    public UserInfoService getService(Integer type){
        return userInfoServiceMap.get(type);
    }
}
