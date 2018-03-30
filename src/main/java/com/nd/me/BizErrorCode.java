package com.nd.me;


import org.springframework.http.HttpStatus;

/**
 * Created by bowen on 2017/2/21 0021.
 * 异常代码封装
 */
public enum BizErrorCode {
    UNAUTHORIZED("DEMO/UNAUTHORIZED", "用户授权错误", HttpStatus.UNAUTHORIZED),

    ARGUMENT_INVALID("DEMO/ARGUMENT_INVALID", "请求参数不合法", HttpStatus.BAD_REQUEST),
    ARGUMENT_REQUIRED("DEMO/ARGUMENT_REQUIRED", "缺少参数", HttpStatus.BAD_REQUEST),
    FILE_NOT_FOUND("DEMO/NOT_FOUND", "用户不存在", HttpStatus.NOT_FOUND),
    USER_EXIST_ALREADY("DEMO/USER_EXIST_ALREADY", "用户已存在", HttpStatus.BAD_REQUEST),

    FORBIDDEN_RESOURCE("DEMO/NOT_AUTHORIZED_USER", "用户权限不足", HttpStatus.FORBIDDEN),
    INTERNAL_SERVER_ERROR("DEMO/INTERNAL_SERVER_ERROR","系统内部错误",HttpStatus.INTERNAL_SERVER_ERROR);

    private String code;    // 异常代码
    private String message; // 异常信息
    private HttpStatus status; // http 状态码

    BizErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
