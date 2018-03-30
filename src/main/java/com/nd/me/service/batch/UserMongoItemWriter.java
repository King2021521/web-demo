package com.nd.me.service.batch;

import com.nd.me.entity.User;
import com.nd.me.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class UserMongoItemWriter implements ItemWriter<User> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public void write(List<? extends User> list) throws Exception {
        for(User user : list){
            logger.info("*****结果是:【id:{},userId:{},userName:{},deparment:{},date:{}】",user.getId(),user.getUserId(),user.getUserName(),user.getDepartment(),user.getCreateTime());
            userInfoService.insert(user);
        }
    }
}
