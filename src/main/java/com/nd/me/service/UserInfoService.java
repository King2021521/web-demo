package com.nd.me.service;

import com.nd.me.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public interface UserInfoService {
    void insert(User record);
    User getUserInfo(String id);
    List<User> getAllUserInfo(int offset,int limit);
}
