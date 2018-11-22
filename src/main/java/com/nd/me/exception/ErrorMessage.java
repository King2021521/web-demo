package com.nd.me.exception;

import com.nd.gaea.client.exception.ResponseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @Author
 * @Description
 * @Date Create in 上午 8:57 2018/11/19 0019
 */
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = -5401402542472113075L;
    private HttpStatus status;
    private String code;
    private String message;
    private String detail;
    private ResponseErrorMessage cause;

    public ErrorMessage() {
    }

    public ErrorMessage(String code) {
        this(null,code, (String) null, (String) null);
    }

    public ErrorMessage(HttpStatus status, String code, String message) {
        this(status, code, message, (String) null);
    }

    public ErrorMessage(HttpStatus status, String code, String message, String detail) {
        this(status, code, message, detail, (ResponseErrorMessage) null);
    }

    public ErrorMessage(HttpStatus status,String code, String message, String detail, ResponseErrorMessage cause) {
        this.status = status;
        this.message = StringUtils.isEmpty(message) ? "null($WAF)" : message;
        this.code = code;
        this.detail = detail;
        this.cause = cause;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ResponseErrorMessage getCause() {
        return this.cause;
    }

    public void setCause(ResponseErrorMessage cause) {
        this.cause = cause;
    }
}
