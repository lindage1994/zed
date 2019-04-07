package com.example.zed.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Auther: zed
 * @Date: 2019/4/7 20:13
 * @Description: redis session 配置类
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 120)
public class RedisSessionConfig {
}
