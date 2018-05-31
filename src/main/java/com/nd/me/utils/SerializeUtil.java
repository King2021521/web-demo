package com.nd.me.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 对象序列化和反序列化工具类
 * @author zxm
 * @since 2017/1/17
 *
 */
@Component
public class SerializeUtil {
    private final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);
    /**
     * 序列化
     * 
     * @param obj
     * @return
     * @throws JsonProcessingException 
     * @throws IOException
     * @since
     */
    public String serialize(Object object) throws Exception{
        Assert.notNull(object);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    
    /**
     * 反序列化
     * 
     * @param serStr
     * @return
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @throws IOException
     * @since
     */
    public <T> T deserialize(String jsonString,Class<T> T) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper(); 
        return objectMapper.readValue(jsonString, T);
    }
}
