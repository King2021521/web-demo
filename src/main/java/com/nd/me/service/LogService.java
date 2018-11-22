package com.nd.me.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @Author
 * @Description
 * @Date Create in 下午 4:52 2018/11/22 0022
 */
@Service
public class LogService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Object findAll(String keyword){

        Pattern pattern=Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE);
        return mongoTemplate.find(new Query().addCriteria(Criteria.where("message").regex(pattern)), JSONObject.class,"log4j2");
    }
}
