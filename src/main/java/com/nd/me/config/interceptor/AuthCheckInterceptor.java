package com.nd.me.config.interceptor;

import com.nd.me.BizErrorCode;
import com.nd.me.BizException;
import com.nd.me.config.annotation.LoginIgnore;
import com.nd.me.utils.JwtManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCheckInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).getMethod().getAnnotation(LoginIgnore.class) != null) {
                return true;
            }
        }

        String token = httpServletRequest.getHeader("Authorization");
        try {
            JwtManager.validateToken(token);
        } catch (Exception e) {
            log.error("process authorization fail,message = {}", e.getMessage());
            throw new BizException(BizErrorCode.UNAUTHORIZED, "token 验证失败");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
