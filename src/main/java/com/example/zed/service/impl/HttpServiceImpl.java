package com.example.zed.service.impl;

import com.example.zed.service.HttpService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Auther: zed
 * @Date: 2019/4/16 09:32
 * @Description: http请求实现
 */
@Service
public class HttpServiceImpl implements HttpService {
    @Override
    public Object getResult(Map params) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://whois.pconline.com.cn/ipJson.jsp?ip=" + params.get("cityName") + "&json=true";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class ).getBody();
    }

    @Override
    public Object getPostResult(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://whois.pconline.com.cn/ipJson.jsp?ip=" + ip + "&json=true";
        return restTemplate.postForObject(uri,null, String.class);
    }
}
