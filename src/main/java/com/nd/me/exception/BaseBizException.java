package com.nd.me.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nd.gaea.I18NProvider;
import com.nd.gaea.client.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @Author
 * @Description
 * @Date Create in 下午 5:52 2018/11/12 0012
 */
public class BaseBizException extends RuntimeException {
    private ResponseEntity<ErrorMessage> responseEntity;

    public BaseBizException(ResponseEntity<ErrorMessage> responseEntity, Throwable cause) {
        super(((ErrorMessage)responseEntity.getBody()).getMessage(), cause);
        this.responseEntity = responseEntity;
    }

    public BaseBizException(ResponseEntity<ErrorMessage> responseEntity) {
        this((ResponseEntity)responseEntity, (Throwable)null);
    }

    public BaseBizException(ErrorMessage errorMessage, HttpStatus status, Throwable cause) {
        this(new ResponseEntity(errorMessage, status), cause);
    }

    public BaseBizException(ErrorMessage errorMessage, HttpStatus status) {
        this(new ResponseEntity(errorMessage, status));
    }

    public BaseBizException(String code, String message, String detail, HttpStatus status, Throwable cause) {
        this(new ErrorMessage(code, message, detail), status, cause);
    }

    public BaseBizException(String code, String message, String detail, HttpStatus status) {
        this((ErrorMessage)(new ErrorMessage(code, message, detail)), (HttpStatus)status, (Throwable)null);
    }

    public BaseBizException(String code, String message, HttpStatus status, Throwable cause) {
        this(new ErrorMessage(code, message), status, cause);
    }

    public BaseBizException(String code, String message, HttpStatus status) {
        this(code, message, (HttpStatus)status, (Throwable)null);
    }

    public BaseBizException(String code, String message, Throwable cause) {
        this(new ErrorMessage(code, message), HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }

    public BaseBizException(String code, String message) {
        this(code, message, (Throwable)null);
    }

    public ResponseEntity<ErrorMessage> getResponseEntity() {
        return this.responseEntity;
    }

    public ErrorMessage getError() {
        return (ErrorMessage)this.responseEntity.getBody();
    }

    public String getLocalizedMessage() {
        return I18NProvider.getString(this.getMessage());
    }
}
