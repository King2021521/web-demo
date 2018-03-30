package com.nd.me.service.impl;

import com.nd.me.dao.mysql.UserMapperI;
import com.nd.me.entity.User;
import com.nd.me.service.UserInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapperI userInfoDao;

    @Override
    public void insert(User record) {
        userInfoDao.insert(record);

    }

    @Override
    public User getUserInfo(String id) {
        return userInfoDao.findOne(id);
    }

    @Override
    public List<User> getAllUserInfo(int offset,int limit) {
        return userInfoDao.findAll(offset, limit);
    }
}
