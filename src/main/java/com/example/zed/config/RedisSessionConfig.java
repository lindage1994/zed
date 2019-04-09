package com.example.zed.config;

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
public class RedisSessionConfig {
    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                System.out.println("创建的sessionId：" + se.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {
                System.out.println("销毁的sessionId：" + se.getSession().getId());
            }
        };
    }

    @Bean
    public HttpSessionAttributeListener httpSessionAttributeListener() {
        return new HttpSessionAttributeListener() {
            @Override
            public void attributeAdded(HttpSessionBindingEvent se) {
                System.out.println("Attribute添加.......");
                System.out.println("Attribute Name:" + se.getName());
                System.out.println("Attribute Value:" + se.getValue());
            }

            @Override
            public void attributeRemoved(HttpSessionBindingEvent se) {
                System.out.println("Attribute删除.....");
                System.out.println("Attribute Name:" + se.getName());
            }

            @Override
            public void attributeReplaced(HttpSessionBindingEvent se) {
                System.out.println("Attribute覆盖......");
                System.out.println("Attribute Name:" + se.getName());
                System.out.println("Attribute Old Value:" + se.getValue());
            }
        };
    }
}
