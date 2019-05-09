package org.gy.framework.demoutil.http;

import org.springframework.web.client.RestTemplate;

public interface RestTemplateAction<T> {

    public T execute(RestTemplate restTemplate);
}
