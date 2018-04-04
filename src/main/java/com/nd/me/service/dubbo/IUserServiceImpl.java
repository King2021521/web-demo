package com.nd.me.service.dubbo;

public class IUserServiceImpl implements IUserService {
    @Override
    public String getName(String id) {
        return id + System.currentTimeMillis();
    }

    @Override
    public Integer getAge(String id) {
        return id.length();
    }
}
