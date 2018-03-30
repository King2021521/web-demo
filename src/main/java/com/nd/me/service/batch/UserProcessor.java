package com.nd.me.service.batch;

import com.nd.me.dao.mongo.repositories.UserDomain;
import com.nd.me.entity.User;
import org.springframework.batch.item.ItemProcessor;

import java.util.UUID;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class UserProcessor implements ItemProcessor<UserDomain,User>{
    @Override
    public User process(UserDomain userDomain) throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserId(283998L);
        user.setUserName(userDomain.getName());
        user.setAge(Integer.valueOf(userDomain.getAge()));
        user.setDepartment("工程院七部");
        user.setCreateTime(userDomain.getPubDate());
        return user;
    }
}
