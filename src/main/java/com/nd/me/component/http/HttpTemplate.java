package com.nd.me.component.http;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
public class HttpTemplate extends RestTemplate {
    public HttpTemplate(List<HttpMessageConverter<?>> messageConverters){
        super(messageConverters);
    }
}
