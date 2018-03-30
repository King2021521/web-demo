package com.nd.me.dao.mysql;

import com.nd.me.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public interface UserMapperI {
    @Insert("insert into user(id,userId,userName,department,age,createTime) values(#{id},#{userId},#{userName},#{department},#{age},#{createTime})")
    int insert(User record);
    
    @Select("select * from user where id =#{id}")
    User findOne(String id);
    
    @Select("select * from user limit #{offset},#{limit}")
    List<User> findAll(@Param(value = "offset") int offset,@Param(value = "limit") int limit);
    
    @Delete("delete from user where id = #{id}")
    int deleteById(String id);
    
    @Update("update user set userId=#{userId}, userName=#{userName},department=#{department},age={#age},createTime=#{createTime} where id=#{id}")
    int update(User user);
}
