package org.gy.framework.demoutil.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * http交互工具类
 */
public class HttpClientUtil {

    protected static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static final int DEFAULT_CONNECT_TIMEOUT         = 3000;
    public static final int DEFAULT_READ_TIMEOUT            = 5000;
    public static final int DEFAULT_CONNECT_REQUEST_TIMEOUT = 100;

    private static final int MAX_TOTAL = 128;

    private static final int MAX_PER_ROUTE = 32;

    private static final RestTemplate restTemplate;

    static {
        HttpClientBuilder httpBuilder = HttpClients.custom();
        // 长连接保持10秒
        // PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(10, TimeUnit.SECONDS);
        // 保持长连接配置，需要在头添加Keep-Alive
        // httpBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL);// 总连接数
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);// 同路由的并发数
        httpBuilder.setConnectionManager(connectionManager);

        // 重试次数，默认是3次，没有开启
        // HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(3, true);
        // httpBuilder.setRetryHandler(retryHandler);

        // 设置请求头
        List<BasicHeader> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        httpBuilder.setDefaultHeaders(headers);

        HttpClient httpClient = httpBuilder.build();

        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(DEFAULT_READ_TIMEOUT);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT);
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
//        clientHttpRequestFactory.setBufferRequestBody(false);

        restTemplate = new RestTemplate(clientHttpRequestFactory);
    }

    private HttpClientUtil() {

    }

    public static <T> T execute(RestTemplateAction<T> action) {
        try {
            return action.execute(restTemplate);
        } catch (Exception e) {
            logger.error("HttpClientUtil execute error.", e);
        }
        return null;

    }

    public static <T> T get(String url, Class<T> responseType) {
        try {
            return restTemplate.getForObject(url, responseType);
        } catch (Exception e) {
            logger.error("HttpClientUtil get error：{}", url, e);
        }
        return null;
    }

    public static <T> T postBody(String url, String body, Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            return restTemplate.postForObject(url, entity, responseType);
        } catch (Exception e) {
            logger.error("HttpClientUtil postBody error：url={},body={}", url, body, e);
        }
        return null;
    }

    public static <T> T postForm(String url, MultiValueMap<String, Object> params, Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
            return restTemplate.postForObject(url, entity, responseType);
        } catch (Exception e) {
            logger.error("HttpClientUtil postForm error：url={}", url, e);
        }
        return null;
    }

}
