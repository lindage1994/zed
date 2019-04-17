package com.example.zed.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Auther: zed
 * @Date: 2019/4/7 20:13
 * @Description: redis session 配置类
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 120)
@Slf4j
public class RedisSessionConfig {
    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                log.info("创建的sessionId：" + se.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {
                log.info("销毁的sessionId：" + se.getSession().getId());
            }
        };
    }

    @Bean
    public HttpSessionAttributeListener httpSessionAttributeListener() {
        return new HttpSessionAttributeListener() {
            @Override
            public void attributeAdded(HttpSessionBindingEvent se) {
                log.info("Attribute添加.......");
                log.info("Attribute Name:" + se.getName());
                log.info("Attribute Value:" + se.getValue());
            }

            @Override
            public void attributeRemoved(HttpSessionBindingEvent se) {
                log.info("Attribute删除.....");
                log.info("Attribute Name:" + se.getName());
            }

            @Override
            public void attributeReplaced(HttpSessionBindingEvent se) {
                log.info("Attribute覆盖......");
                log.info("Attribute Name:" + se.getName());
                log.info("Attribute Old Value:" + se.getValue());
            }
        };
    }
}
