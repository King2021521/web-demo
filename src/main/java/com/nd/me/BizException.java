package com.nd.me;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;
    private HttpStatus status;


    private Logger logger = LoggerFactory.getLogger(BizException.class);


    /**
     * 统一异常入口
     *
     * @param status
     * @param code
     * @param message
     */
    public BizException(HttpStatus status, String code, String message) {
        super(message);
        this.code = code;
        this.status = status;
        logger.error("Request Faile,HttpCode：" + status.toString() + ",Code:" + code, this);
    }

    /**
     * 统一异常入口 -- ExecptionCode
     *
     * @param exceptionCode 业务统一异常 code
     */
    public BizException(BizErrorCode exceptionCode, String... args) {
        this(exceptionCode.getStatus(), exceptionCode.getCode(), String.format(exceptionCode.getMessage(),args));
        logger.error("Request Faile,HttpCode：" + exceptionCode.getStatus() + ",Code:" + exceptionCode.getCode(), this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
