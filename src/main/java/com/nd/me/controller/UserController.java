package com.nd.me.controller;

import com.alibaba.fastjson.JSONObject;
import com.nd.me.BizException;
import com.nd.me.BizErrorCode;
import com.nd.me.entity.User;
import com.nd.me.entity.UserXml;
import com.nd.me.service.UserInfoService;

import com.nd.me.utils.JwtManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
@RestController
@RequestMapping("/${app.map.ver}/api/userInfo")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/xmls", method = RequestMethod.GET)
    public Object getXml() {
        UserXml userXml = new UserXml();
        userXml.setName("zxm");
        userXml.setAddr("辽宁大连中山");
        return userXml;
    }

    @RequestMapping(value = "/tokens", method = RequestMethod.POST, produces = {"application/json"})
    public JSONObject getToken(@RequestBody JSONObject body) {
        JSONObject resp = new JSONObject();
        String token = JwtManager.token(body.getString("app_id"), body.getString("mch_id"));
        resp.put("access_token", token);
        return resp;
    }

    /**
     * 测试token
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public Object testJwt(HttpServletRequest request) {
        try {
            return JwtManager.validateToken(request.getHeader("Authorization"));
        } catch (Exception e) {
            return new BizException(BizErrorCode.UNAUTHORIZED);
        }

    }

    /**
     * 获取用户详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    public Object getUserInfo(@PathVariable String id) {
        User user = userInfoService.getUserInfo(id);

        return user;
    }

    /**
     * 添加用户
     *
     * @param input
     */
    @RequestMapping(value = "/actions/add_user", method = RequestMethod.POST)
    public Map<String, String> addUser(@RequestBody Map<String, String> input) {
        User record = new User();
        record.setUserName(input.get("user_name"));
        record.setUserId(Long.parseLong(input.get("user_id")));
        record.setDepartment(input.get("department"));
        record.setAge(Integer.parseInt(input.get("age")));
        record.setCreateTime(new Date());

        try {
            userInfoService.insert(record);
        } catch (Exception e) {
            logger.error("用户已存在");
            throw new BizException(BizErrorCode.USER_EXIST_ALREADY);
        }

        Map<String, String> resp = new HashMap<>();
        resp.put("items", "success");
        return resp;
    }

    /**
     * 获取全部用户
     *
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Map<String, Object> getAllUser(@RequestParam(value = "$offset", required = false) Integer offset, @RequestParam(value = "$limit", required = false) Integer limit) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = 10;
        }

        if (offset < 0 || limit < 0) {
            throw new BizException(BizErrorCode.ARGUMENT_INVALID);
        }
        Map<String, Object> resp = new HashMap<>();
        List<User> items = userInfoService.getAllUserInfo(offset, limit);
        resp.put("items", items);
        return resp;
    }
}
