package com.nd.me.component.nio.netty.support;

/**
 * @Author
 * @Description
 * @Date Create in 下午 1:56 2018/9/18 0018
 */
public class MessagePayload<T> {
    private Header header;

    private T body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
