package com.nd.me.service.batch;

import com.nd.me.dao.mongo.repositories.UserDomain;
import com.nd.me.dao.mongo.repositories.UserRepository;
import org.springframework.batch.item.ItemReader;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zxm on 2017/7/17 0017.
 */
public class UserMongoItemReader implements ItemReader<UserDomain>{

    @Resource
    UserRepository userRepository;

    @Override
    public UserDomain read() throws Exception{
        UserDomain userDomain = new UserDomain();
        userDomain.setAge("20");
        userDomain.setName("zxm");
        userDomain.setPubDate(new Date());

        return userDomain;
    }
}
